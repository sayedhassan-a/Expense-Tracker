package com.example.expenseTracker.expense.service;

import com.example.expenseTracker.category.model.Category;
import com.example.expenseTracker.category.service.CategoryService;
import com.example.expenseTracker.exceptionHandling.exception.UnAuthorizedAccessException;
import com.example.expenseTracker.expense.repository.ExpenseRepository;
import com.example.expenseTracker.expense.model.Expense;
import com.example.expenseTracker.user.model.User;
import com.example.expenseTracker.user.service.UserService;
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
    @Override
    public List<Expense> findAll(Pageable pageable) {
        return expenseRepository.findByUserId(userService.findCurrentUser().getId(), pageable);
    }

    @Override
    public Expense findById(int id) {
        return expenseRepository.findByIdAndUserId(id,userService.findCurrentUser().getId());
    }
    @Override
    public Expense save(Expense request) {
        User user = userService.findCurrentUser();
        Category category = categoryService.findById(request.getCategory().getId());
        Expense expense = Expense.
                builder().
                user(user).
                category(category).
                amount(request.getAmount()).
                date(request.getDate()).
                build();
        return expenseRepository.save(expense);
    }
    @Override
    public Expense update(Expense request) {
        Optional<Expense> dbExpense = expenseRepository.findById(request.getId());
        User user = userService.findCurrentUser();
        Category category = categoryService.findById(request.getCategory().getId());
        if(dbExpense.isPresent() && dbExpense.get().getUser().equals(user)) {
            Expense expense = Expense.
                    builder().
                    user(user).
                    category(category).
                    amount(request.getAmount()).
                    date(request.getDate()).
                    build();
            return expenseRepository.save(expense);
        }
        else
            throw new UnAuthorizedAccessException("Access Denied");
    }

    @Override
    public void deleteById(int id) {
        Expense expense = expenseRepository.findByIdAndUserId(id, userService.findCurrentUser().getId());
        if(expense == null)
        {
            throw new RuntimeException("item not found");

        }
        else expenseRepository.deleteById(id);
    }


}
