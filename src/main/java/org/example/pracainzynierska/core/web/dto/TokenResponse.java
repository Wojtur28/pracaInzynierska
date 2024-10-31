package org.example.pracainzynierska.core.web.dto;

public record TokenResponse(
        String accessToken,
        int expiresIn
) {
}
