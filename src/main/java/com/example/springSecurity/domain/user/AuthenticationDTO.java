package com.example.springSecurity.domain.user;

public record AuthenticationDTO (
        String login,
        String password)
{}
