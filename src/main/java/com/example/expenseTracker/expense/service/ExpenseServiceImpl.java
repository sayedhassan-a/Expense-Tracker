package com.example.expenseTracker.expense.service;

import com.example.expenseTracker.category.model.Category;
import com.example.expenseTracker.category.service.CategoryService;
import com.example.expenseTracker.exceptionHandling.exception.NotFoundException;
import com.example.expenseTracker.exceptionHandling.exception.UnAuthorizedAccessException;
import com.example.expenseTracker.expense.dto.ExpenseDTO;
import com.example.expenseTracker.expense.repository.ExpenseRepository;
import com.example.expenseTracker.expense.model.Expense;
import com.example.expenseTracker.user.model.User;
import com.example.expenseTracker.user.service.UserService;
import com.example.expenseTracker.util.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final EmailService emailService;

    @Override
    public List<Expense> findAll(Pageable pageable) {
        return expenseRepository.findByUserId(userService.findCurrentUser().getId(), pageable);
    }

    @Override
    public Expense findById(int id) {
        Expense expense = expenseRepository.findByIdAndUserId(id,
                userService.findCurrentUser().getId());
        if(expense == null)throw new UnAuthorizedAccessException("Access Denied");
        return expense;
    }

    @Override
    public Expense save(ExpenseDTO request) {
        User user = userService.findCurrentUser();

        //Check if a previous category with the same name exists to use it
        Optional<Category> dbCategory =
                categoryService.findByName(request.getCategory());

        //If no previous category exists create new category with this name
        Category category =
                dbCategory.orElseGet(() -> Category.builder().
                        name(request.getCategory()).
                        user(user).
                        build());

        //Creating an expense object
        Expense expense = Expense.
                builder().
                id(request.getId()).
                user(user).
                category(category).
                amount(request.getAmount()).
                date(request.getDate()).
                build();

        //Saving the object to the database
        Expense save = expenseRepository.save(expense);

        //Check if there is a budget for the current user
        if (user.getBudget() != null) {
            LocalDate start = LocalDate.now().withDayOfMonth(1);
            LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
            Long totalExpense =
                    expenseRepository.totalSumByDateAndUserId(start, end,
                            user.getId());

            //Send an email notification if the budget for this month has been exceeded
            if (totalExpense > user.getBudget().getAmount()) {
                String subject = "Budget Exceeded";
                String body = "You have exceeded the budget for this month";
                emailService.sendEmail(user.getEmail(), subject, body);
            }
        }
        return save;
    }

    @Override
    public Expense update(ExpenseDTO request) {
        Optional<Expense> dbExpense =
                expenseRepository.findById(request.getId());
        User user = userService.findCurrentUser();
        if (dbExpense.isPresent() && dbExpense.get().getUser().equals(user)) {
            return save(request);
        } else
            throw new UnAuthorizedAccessException("Access Denied");
    }

    @Override
    public void deleteById(int id) {
        Expense expense = expenseRepository.findByIdAndUserId(id,
                userService.findCurrentUser().getId());
        if (expense == null) {
            throw new UnAuthorizedAccessException("Access Denied");

        } else expenseRepository.deleteById(id);
    }


}
