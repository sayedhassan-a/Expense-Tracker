package com.example.expenseTracker.income.controller;

import com.example.expenseTracker.exceptionHandling.exception.UnAuthorizedAccessException;
import com.example.expenseTracker.income.model.Income;
import com.example.expenseTracker.income.service.IncomeService;
import com.example.expenseTracker.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class IncomeRestController {
    private final IncomeService incomeService;
    private final UserService userService;

    @GetMapping("/income")
    public ResponseEntity<List<Income>> findAll(
            @RequestParam(name = "p", defaultValue = "0") int page,
            @RequestParam(name = "s", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(incomeService.findAll(pageable));
    }

    @GetMapping("/income/{id}")
    public ResponseEntity<Income> findById(@PathVariable int id) {
        return ResponseEntity.ok(incomeService.findById(id));
    }

    @PostMapping("/income")
    public ResponseEntity<Income> save(@RequestBody Income income) {
        return ResponseEntity.ok(incomeService.save(income));
    }

    @PutMapping("/income")
    public ResponseEntity<Income> update(@RequestBody Income income) {
        return ResponseEntity.ok(incomeService.update(income));

    }

    @DeleteMapping("/income/{id}")
    public ResponseEntity<String> Delete(@PathVariable int id) {
        incomeService.delete(id);
        return ResponseEntity.ok("Object Deleted Successfully");
    }
}
