package com.example.expenseTracker.category.controller;

import com.example.expenseTracker.category.model.Category;
import com.example.expenseTracker.category.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> findByID(@PathVariable int id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping("/category")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.save(category));
    }

    @PutMapping("/category")
    public ResponseEntity<Category> update(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.update(category));
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        categoryService.delete(id);
        return ResponseEntity.ok("category deleted successfulyl");
    }

}
