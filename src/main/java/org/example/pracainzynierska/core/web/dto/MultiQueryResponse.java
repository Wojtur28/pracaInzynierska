package org.example.pracainzynierska.core.web.dto;

import java.util.Set;

public record MultiQueryResponse(
        String name,
        Set<GameResponse> result
) {}
