package org.example.pracainzynierska.mapper;

import com.example.model.CreateGameRating;
import com.example.model.GameRating;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {GameRatingMapper.class, BaseMapper.class, DateTimeMapper.class})
public interface GameRatingMapper {

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "uuidToUserEntity")
    GameRatingEntity toEntity(GameRating gameRating);

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userEntityToUuid")
    GameRating toDto(GameRatingEntity gameRatingEntity);

    List<GameRating> toDto(List<GameRatingEntity> gameRatingEntities);

    List<GameRatingEntity> toEntity(List<GameRating> gameRatings);

    GameRatingEntity toEntity(CreateGameRating createGameRating);
}
