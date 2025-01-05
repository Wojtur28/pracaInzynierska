package org.example.pracainzynierska.core.usecase.rating;

import com.example.model.GameRatingAnswer;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingAnswerEntity;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingAnswerRepository;
import org.example.pracainzynierska.mapper.GameRatingAnswerMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetGameRatingAnswerUseCase {

    private final GameRatingAnswerRepository gameRatingAnswerRepository;
    private final GameRatingAnswerMapper gameRatingAnswerMapper;

    public List<GameRatingAnswer> getAnswersForRating(UUID ratingId) {

        List<GameRatingAnswerEntity> gameRatingAnswerEntities = gameRatingAnswerRepository.findBygameRatingEntity_Id(ratingId);

        return gameRatingAnswerMapper.toDto(gameRatingAnswerEntities);
    }

}
