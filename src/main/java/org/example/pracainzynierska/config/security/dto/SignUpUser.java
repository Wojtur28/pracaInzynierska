package org.example.pracainzynierska.config.security.dto;

import org.example.pracainzynierska.core.entities.user.Gender;

import java.time.LocalDate;

public record SignUpUser(
        String firstName,
        String lastName,
        String email,
        String password,
        Gender gender,
        LocalDate dateOfBirth
) {}
