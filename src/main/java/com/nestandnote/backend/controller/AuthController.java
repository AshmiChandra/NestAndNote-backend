package com.nestandnote.backend.controller;

import com.nestandnote.backend.dto.AuthDtos.LoginRequest;
import com.nestandnote.backend.dto.AuthDtos.RegisterRequest;
import com.nestandnote.backend.dto.AuthDtos.UserResponse;
import com.nestandnote.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration done");
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}