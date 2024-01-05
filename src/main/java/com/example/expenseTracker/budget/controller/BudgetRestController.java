package com.example.expenseTracker.budget.controller;

import com.example.expenseTracker.budget.model.Budget;
import com.example.expenseTracker.budget.service.BudgetService;
import com.example.expenseTracker.exceptionHandling.exception.UnAuthorizedAccessException;
import com.example.expenseTracker.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BudgetRestController {
    private final BudgetService budgetService;
    private final UserService userService;

    @GetMapping("/budget")
    public ResponseEntity<List<Budget>> findAll() {
        return ResponseEntity.ok(budgetService.findAll());
    }

    @GetMapping("/budget/{id}")
    public ResponseEntity<Budget> findById(@PathVariable int id) {
        return ResponseEntity.ok(budgetService.findById(id));
    }

    @PostMapping("/budget")
    public ResponseEntity<Budget> save(@RequestBody Budget budget) {
        return ResponseEntity.ok(budgetService.save(budget));
    }

    @PutMapping("/budget")
    public ResponseEntity<Budget> update(@RequestBody Budget budget) {
        return ResponseEntity.ok(budgetService.update(budget));

    }

    @DeleteMapping("/budget/{id}")
    public ResponseEntity<String> Delete(@PathVariable int id) {
        budgetService.delete(id);
        return ResponseEntity.ok("Object deletd successfully");

    }

}
