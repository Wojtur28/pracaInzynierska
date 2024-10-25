package org.example.pracainzynierska.core.web.dto;

import java.util.List;
import java.util.UUID;

public record GameResponse(UUID id,
                           String name,
                           List<GenreResponse> genres,
                           List<ThemeResponse> themes) {
}
