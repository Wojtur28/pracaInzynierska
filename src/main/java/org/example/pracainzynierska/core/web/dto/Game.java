package org.example.pracainzynierska.core.web.dto;

import java.util.List;

public record Game(
        Long id,
        String name,
        List<Genre> genres,
        List<Theme> themes,
        List<Screenshot> screenshots,
        Double rating,
        List<Platform> platforms
) {
    public record Genre(Long id, String name) {}
    public record Theme(Long id, String name) {}
    public record Screenshot(Long id, String image_id) {}
    public record Platform(Long id, String name) {}
}
