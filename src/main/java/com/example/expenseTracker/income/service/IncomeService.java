package com.example.expenseTracker.income.service;

import com.example.expenseTracker.income.model.Income;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IncomeService {
    List<Income> findAll(Pageable page);

    Income findById(int id);

    Income save(Income budget);

    Income update(Income budget);

    void delete(int id);
}
