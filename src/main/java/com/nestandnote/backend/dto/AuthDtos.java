package com.nestandnote.backend.dto;

public class AuthDtos {

    public record RegisterRequest(String name, String email, String password) {}

    public record LoginRequest(String email, String password) {}

    public record UserResponse(Long id, String name, String email) {}
}