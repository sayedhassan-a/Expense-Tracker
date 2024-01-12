package com.example.expenseTracker.expense.controller;

import com.example.expenseTracker.expense.dto.ExpenseDTO;
import com.example.expenseTracker.expense.model.Expense;
import com.example.expenseTracker.expense.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ExpenseRestController {
    private final ExpenseService expenseService;

    @GetMapping("/expense")
    public ResponseEntity<List<Expense>> findALl(
            @RequestParam(name = "p", defaultValue = "0") int page,
            @RequestParam(name = "s", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(expenseService.findAll(pageable));
    }

    @GetMapping("/expense/{id}")
    public ResponseEntity<Expense> findById(@PathVariable int id) {
        return ResponseEntity.ok(expenseService.findById(id));
    }

    @PostMapping("/expense")
    public ResponseEntity<Expense> save(@RequestBody ExpenseDTO expense) {
        return ResponseEntity.ok(expenseService.save(expense));
    }

    @PutMapping("/expense")
    public ResponseEntity<Expense> update(@RequestBody ExpenseDTO expense) {
        return ResponseEntity.ok(expenseService.update(expense));
    }

    @DeleteMapping("/expense/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        expenseService.deleteById(id);
        return ResponseEntity.ok("Expense deleted successfully");
    }

}
