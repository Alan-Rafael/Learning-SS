package com.example.springSecurity.domain.user;

public record RegisterDTO (
        String login,
        String password,
        UserRole role
){
}
