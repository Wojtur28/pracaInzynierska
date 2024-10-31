package org.example.pracainzynierska.core.mapper;

import org.example.pracainzynierska.core.entities.theme.ThemeEntity;
import org.example.pracainzynierska.core.web.dto.GameResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ThemeMapper {

    @Mapping(target = "id", ignore = true)           // UUID primary key
    @Mapping(target = "apiId", source = "id")        // Map the API ID from DTO to entity
    ThemeEntity toEntity(GameResponse.Theme theme);

    @Mapping(target = "id", source = "apiId")        // Map the API ID from entity to DTO
    GameResponse.Theme toDto(ThemeEntity themeEntity);
}
