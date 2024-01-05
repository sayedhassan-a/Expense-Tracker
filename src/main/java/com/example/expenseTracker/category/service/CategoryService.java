package com.example.expenseTracker.category.service;

import com.example.expenseTracker.category.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
    Category save(Category category);
    Category update(Category category);
    void delete(int id);
}
