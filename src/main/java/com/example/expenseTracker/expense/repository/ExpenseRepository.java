package com.example.expenseTracker.expense.repository;

import com.example.expenseTracker.expense.model.Expense;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
    public List<Expense> findByUserId(int userId, Pageable pageable);
    public Expense findByIdAndUserId(int id, int userId);
}
