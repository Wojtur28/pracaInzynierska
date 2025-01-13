package org.example.pracainzynierska.mapper;

import com.example.model.GameRatingAnswer;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingAnswerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {BaseMapper.class, DateTimeMapper.class})
public interface GameRatingAnswerMapper {

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userEntityToUuid")
    GameRatingAnswer toDto(GameRatingAnswerEntity entity);

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "uuidToUserEntity")
    GameRatingAnswerEntity toEntity(GameRatingAnswer dto);

    List<GameRatingAnswer> toDto(List<GameRatingAnswerEntity> gameRatingAnswerEntities);

    List<GameRatingAnswerEntity> toEntity(List<GameRatingAnswer> gameRatingAnswers);
}
