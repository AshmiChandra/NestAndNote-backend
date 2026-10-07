package com.nestandnote.backend.controller;

import com.nestandnote.backend.dto.LoginData;
import com.nestandnote.backend.model.User;
import com.nestandnote.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    UserRepository ur;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/register")
    String register(@RequestBody User u) {
        if (ur.existsByEmail(u.getEmail())) {
            return "Email already registered";
        }
        u.setId(null);                                   // always create a new row
        u.setPassword(encoder.encode(u.getPassword()));  // hash it
        ur.save(u);
        return "Registration done";
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginData l) {
        User eu = ur.findByEmail(l.getEmail()).orElse(null);
        if (eu != null && encoder.matches(l.getPassword(), eu.getPassword())) {
            return ResponseEntity.ok(Map.of(
                    "id", eu.getId(),
                    "name", eu.getName(),
                    "email", eu.getEmail()
            ));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
    }
}