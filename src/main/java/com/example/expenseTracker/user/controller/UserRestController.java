package com.example.expenseTracker.user.controller;

import com.example.expenseTracker.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private final UserService userService;
    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/user")
    public ResponseEntity<String> findCurrentUser() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.findCurrentUser().getUsername());
    }

}
