package org.example.pracainzynierska.core.web.dto;

import java.util.List;

public record GameResponse(Long id,
                           String name,
                           List<Long> genres,
                           List<Long> themes,
                           List<Long> screenshots,
                           List<Long> platforms) {
}
