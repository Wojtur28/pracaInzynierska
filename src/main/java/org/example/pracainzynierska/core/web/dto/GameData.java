package org.example.pracainzynierska.core.web.dto;


import java.util.List;

public record GameData(
        String name,
        int category,
        List<Genre> genres,
        List<Theme> themes,
        List<String> screenshots,
        double rating,
        List<Platform> platforms
) {}
