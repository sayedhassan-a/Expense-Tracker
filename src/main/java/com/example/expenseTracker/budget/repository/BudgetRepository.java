package com.example.expenseTracker.budget.repository;

import com.example.expenseTracker.budget.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    public List<Budget> findAllByUserId(int userId);
    public Budget findByIdAndUserId(int id, int userId);
}
