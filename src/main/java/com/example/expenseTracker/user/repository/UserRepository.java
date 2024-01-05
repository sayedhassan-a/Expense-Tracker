package com.example.expenseTracker.user.repository;

import com.example.expenseTracker.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
