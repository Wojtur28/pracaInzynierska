package org.example.pracainzynierska.core.web.dto;

import java.util.List;

public record MultiQueryResponse(
        String name,
        List<GameResponse> result
) {}
