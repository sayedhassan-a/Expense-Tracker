package com.example.expenseTracker.auth;

import com.example.expenseTracker.security.JwtService;
import com.example.expenseTracker.user.repository.UserRepository;
import com.example.expenseTracker.user.model.ROLE;
import com.example.expenseTracker.user.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    public AuthenticationService(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstname());
        user.setLastName(request.getLastname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(ROLE.USER);
        var savedUser =userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername());
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
