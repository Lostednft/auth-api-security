package com.example.auth_api_security.dtos.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
