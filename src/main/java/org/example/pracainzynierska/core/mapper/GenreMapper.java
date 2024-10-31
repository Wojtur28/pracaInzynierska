package org.example.pracainzynierska.core.mapper;

import org.example.pracainzynierska.core.entities.genre.GenreEntity;
import org.example.pracainzynierska.core.web.dto.GameResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apiId", source = "id")
    GenreEntity toEntity(GameResponse.Genre genre);

    @Mapping(target = "id", source = "apiId")
    GameResponse.Genre toDto(GenreEntity genreEntity);
}
