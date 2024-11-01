package org.example.pracainzynierska.core.mapper;

import org.example.pracainzynierska.core.entities.theme.ThemeEntity;
import org.example.pracainzynierska.core.web.dto.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ThemeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apiId", source = "id")
    ThemeEntity toEntity(Game.Theme theme);

    @Mapping(target = "id", source = "apiId")
    Game.Theme toDto(ThemeEntity themeEntity);
}
