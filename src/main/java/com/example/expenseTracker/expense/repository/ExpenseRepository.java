package com.example.expenseTracker.expense.repository;

import com.example.expenseTracker.expense.model.Expense;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
    public List<Expense> findByUserId(int userId, Pageable pageable);
    public Expense findByIdAndUserId(int id, int userId);
    @Query("select coalesce(sum(e.amount), 0) from Expense e where e.user.id = :userId and e.date >= :start and e.date <= :end")
    Long totalSumByDateAndUserId(@Param("start") LocalDate start,@Param("end")LocalDate end, @Param("userId") int userId);
}
