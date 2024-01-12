package com.example.expenseTracker.category.service;

import com.example.expenseTracker.category.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
    Optional<Category> findByName(String name);
    Category save(Category category);
    Category update(Category category);
    void delete(int id);
}
