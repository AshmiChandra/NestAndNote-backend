package com.nestandnote.backend.service;

import com.nestandnote.backend.dto.AuthDtos.LoginRequest;
import com.nestandnote.backend.dto.AuthDtos.RegisterRequest;
import com.nestandnote.backend.dto.AuthDtos.UserResponse;
import com.nestandnote.backend.entity.User;
import com.nestandnote.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }

    public UserResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new SecurityException("Invalid email or password"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new SecurityException("Invalid email or password");
        }

        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}