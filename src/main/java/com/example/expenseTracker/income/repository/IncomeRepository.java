package com.example.expenseTracker.income.repository;

import com.example.expenseTracker.income.model.Income;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income,Integer> {
    public Income findByIdAndUserId(int id, int userId);
    public List<Income> findByUserId(int userId, Pageable pageable);
}
