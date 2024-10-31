package org.example.pracainzynierska.core.mapper;

import org.example.pracainzynierska.core.entities.game.GameEntity;
import org.example.pracainzynierska.core.web.dto.Game;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        uses = {GenreMapper.class, ThemeMapper.class, PlatformMapper.class, ScreenshotMapper.class}
)
public interface GameMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apiId", source = "id")
    @Mapping(target = "apiRating", source = "rating")
    GameEntity toEntity(Game game);

    @Mapping(target = "id", source = "apiId")
    @Mapping(target = "rating", source = "apiRating")
    Game toDto(GameEntity gameEntity);
}

