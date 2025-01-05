package org.example.pracainzynierska.mapper;

import com.example.model.GameRatingAnswer;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingAnswerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {BaseMapper.class, DateTimeMapper.class})
public interface GameRatingAnswerMapper {

    GameRatingAnswer toDto(GameRatingAnswerEntity gameRatingAnswerEntity);

    GameRatingAnswerEntity toEntity(GameRatingAnswer gameRatingAnswer);

    List<GameRatingAnswer> toDto(List<GameRatingAnswerEntity> gameRatingAnswerEntities);

    List<GameRatingAnswerEntity> toEntity(List<GameRatingAnswer> gameRatingAnswers);
}
