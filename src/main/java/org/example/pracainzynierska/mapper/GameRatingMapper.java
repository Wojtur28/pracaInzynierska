package org.example.pracainzynierska.mapper;

import com.example.model.GameRating;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {GameRatingMapper.class, BaseMapper.class})
public interface GameRatingMapper {

    GameRatingEntity toEntity(GameRating gameRating);

    GameRating toModel(GameRatingEntity gameRatingEntity);

    List<GameRating> toModel(List<GameRatingEntity> gameRatingEntities);

    List<GameRatingEntity> toEntity(List<GameRating> gameRatings);
}
