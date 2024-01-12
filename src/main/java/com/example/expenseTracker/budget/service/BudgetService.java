package com.example.expenseTracker.budget.service;

import com.example.expenseTracker.budget.model.Budget;

import java.util.List;
import java.util.Optional;

public interface BudgetService {

    Optional<Budget> find();

    Optional<Budget> findById(int id);

    Budget save(Budget budget);

    Budget update(Budget budget);

    void delete(int id);
}
