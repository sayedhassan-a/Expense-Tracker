package com.example.expenseTracker.user.service;

import com.example.expenseTracker.user.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService {

    UserDetails findByUsername(String username)throws UsernameNotFoundException;
    User findById(int id);
    User findCurrentUser();
}
