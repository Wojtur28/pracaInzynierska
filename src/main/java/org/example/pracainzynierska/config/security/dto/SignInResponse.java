package org.example.pracainzynierska.config.security.dto;

public record SignInResponse(
        String token,
        Integer expiresIn
) {}
