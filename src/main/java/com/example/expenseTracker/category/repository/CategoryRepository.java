package com.example.expenseTracker.category.repository;

import com.example.expenseTracker.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findByIsCustomFalseOrUserId(int userId);
    Category findByIdAndIsCustomFalseOrIdAndUserId(int id,int id2, int userId);
}
