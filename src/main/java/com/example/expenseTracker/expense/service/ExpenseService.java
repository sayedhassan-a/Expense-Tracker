package com.example.expenseTracker.expense.service;

import com.example.expenseTracker.expense.dto.ExpenseDTO;
import com.example.expenseTracker.expense.model.Expense;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExpenseService {

    List<Expense> findAll(Pageable pageable);
    Expense findById(int id);
    Expense save(ExpenseDTO expense);
    Expense update(ExpenseDTO expense);
    void deleteById(int id);
}
