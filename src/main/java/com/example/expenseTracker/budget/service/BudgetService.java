package com.example.expenseTracker.budget.service;

import com.example.expenseTracker.budget.model.Budget;

import java.util.List;

public interface BudgetService {
    List<Budget> findAll();

    Budget findById(int id);

    Budget save(Budget budget);

    Budget update(Budget budget);

    void delete(int id);
}
