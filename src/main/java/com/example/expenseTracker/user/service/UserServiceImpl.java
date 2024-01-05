package com.example.expenseTracker.user.service;


import com.example.expenseTracker.user.model.User;
import com.example.expenseTracker.user.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())return user.get();
        throw new RuntimeException("User Not Found");
    }

    @Override
    public User findCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByUsername(username);

    }


}
