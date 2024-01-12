package com.example.expenseTracker.budget.service;

import com.example.expenseTracker.budget.model.Budget;
import com.example.expenseTracker.budget.repository.BudgetRepository;
import com.example.expenseTracker.category.service.CategoryService;
import com.example.expenseTracker.exceptionHandling.exception.UnAuthorizedAccessException;
import com.example.expenseTracker.user.model.User;
import com.example.expenseTracker.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepository budgetRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public Optional<Budget> find() {
        return budgetRepository.findByUserId(userService.findCurrentUser().getId());
    }

    @Override
    public Optional<Budget> findById(int id) {
        return budgetRepository.findById(id);
    }

    @Override
    public Budget save(Budget request) {
        User user = userService.findCurrentUser();
        Budget budget = Budget.
                builder().
                user(user).
                amount(request.getAmount()).
                build();
        return budgetRepository.save(budget);
    }

    @Override
    public Budget update(Budget request) {
        Optional<Budget> dbBudget = findById(request.getId());
        User user = userService.findCurrentUser();
        if (dbBudget.isPresent() && dbBudget.get().getUser().equals(user)) {
            Budget budget = Budget.
                    builder().
                    id(request.getId()).
                    user(user).
                    amount(request.getAmount()).
                    build();
            return budgetRepository.save((budget));
        } else
            throw new UnAuthorizedAccessException("Access Denied");
    }

    @Override
    public void delete(int id) {
        Optional<Budget> dbBudget = findById(id);
        if (dbBudget.isPresent() && dbBudget.get().getUser().equals(userService.findCurrentUser())) {
            budgetRepository.delete(dbBudget.get());
        } else
            throw new UnAuthorizedAccessException("Access Denied");
    }
}
