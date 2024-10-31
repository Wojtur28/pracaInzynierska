package org.example.pracainzynierska.core.mapper;

import org.example.pracainzynierska.core.entities.theme.ThemeEntity;
import org.example.pracainzynierska.core.web.dto.GameResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ThemeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apiId", source = "id")
    ThemeEntity toEntity(GameResponse.Theme theme);

    @Mapping(target = "id", source = "apiId")
    GameResponse.Theme toDto(ThemeEntity themeEntity);
}
