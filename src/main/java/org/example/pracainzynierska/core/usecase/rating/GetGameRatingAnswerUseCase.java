package org.example.pracainzynierska.core.usecase.rating;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingAnswerEntity;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetGameRatingAnswerUseCase {

    private final GameRatingAnswerRepository gameRatingAnswerRepository;

    public List<GameRatingAnswerEntity> getAnswersForRating(UUID ratingId) {
        return gameRatingAnswerRepository.findBygameRatingEntity_Id(ratingId);
    }


}
