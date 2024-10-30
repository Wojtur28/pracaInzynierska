package org.example.pracainzynierska.core.web.dto;

import java.util.List;

public record GameResponse(
        String name,
        int category,
        List<String> genres,
        List<String> themes,
        List<String> screenshots,
        double rating,
        List<String> platforms
) {}
