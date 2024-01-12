package com.example.expenseTracker.category.repository;

import com.example.expenseTracker.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findByUserId(int userId);
    Category findByIdAndUserId(int id, int userId);
    Optional<Category> findByNameAndUserId(String name, int userId);
}
