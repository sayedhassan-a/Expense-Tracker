package com.example.expenseTracker.budget.repository;

import com.example.expenseTracker.budget.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    Optional<Budget> findByUserId(int userId);
}
