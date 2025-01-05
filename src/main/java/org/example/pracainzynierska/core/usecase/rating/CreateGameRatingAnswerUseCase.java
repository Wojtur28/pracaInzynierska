package org.example.pracainzynierska.core.usecase.rating;

import com.example.model.CreateGameRatingAnswer;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingAnswerEntity;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingAnswerRepository;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateGameRatingAnswerUseCase {

    private final GameRatingRepository gameRatingRepository;
    private final GameRatingAnswerRepository gameRatingAnswerRepository;

    public GameRatingAnswerEntity createAnswer(UUID ratingId, CreateGameRatingAnswer createGameRatingAnswer) {
        GameRatingAnswerEntity gameRatingAnswerEntity = new GameRatingAnswerEntity();
        gameRatingAnswerEntity.setContent(createGameRatingAnswer.getContent());
        gameRatingAnswerEntity.setGameRatingEntity(gameRatingRepository.findById(ratingId)
                .orElseThrow(() -> new EntityNotFoundException("Rating not found")));
        return gameRatingAnswerRepository.save(gameRatingAnswerEntity);
    }
}
