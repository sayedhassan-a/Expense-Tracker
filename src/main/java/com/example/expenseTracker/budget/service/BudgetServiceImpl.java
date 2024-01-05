package com.example.expenseTracker.budget.service;

import com.example.expenseTracker.budget.model.Budget;
import com.example.expenseTracker.budget.repository.BudgetRepository;
import com.example.expenseTracker.category.model.Category;
import com.example.expenseTracker.category.service.CategoryService;
import com.example.expenseTracker.exceptionHandling.exception.UnAuthorizedAccessException;
import com.example.expenseTracker.user.model.User;
import com.example.expenseTracker.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepository budgetRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public List<Budget> findAll() {
        return budgetRepository.findAllByUserId(userService.findCurrentUser().getId());
    }

    @Override
    public Budget findById(int id) {
        return budgetRepository.findByIdAndUserId(id, userService.findCurrentUser().getId());
    }

    @Override
    public Budget save(Budget request) {
        User user = userService.findCurrentUser();
        Category category = categoryService.findById(request.getCategory().getId());
        Budget budget = Budget.
                builder().
                user(user).
                category(category).
                amount(request.getAmount()).
                period(request.getPeriod()).
                build();
        return budgetRepository.save(budget);
    }

    @Override
    public Budget update(Budget request) {
        Budget dbBudget = findById(request.getId());
        User user = userService.findCurrentUser();
        Category category = categoryService.findById(request.getCategory().getId());
        if (dbBudget != null && dbBudget.getUser().equals(user)) {
            Budget budget = Budget.
                    builder().
                    id(request.getId()).
                    user(user).
                    category(category).
                    amount(request.getAmount()).
                    period(request.getPeriod()).
                    build();
            return budgetRepository.save((budget));
        } else
            throw new UnAuthorizedAccessException("Access Denied");
    }

    @Override
    public void delete(int id) {
        Budget dbBudget = findById(id);
        if (dbBudget != null && dbBudget.getUser().equals(userService.findCurrentUser())) {
            budgetRepository.delete(dbBudget);
        } else
            throw new UnAuthorizedAccessException("Access Denied");
    }
}
