package org.example.pracainzynierska.core.usecase.rating;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DeleteGameRatingAnswerUseCase {

    private final GameRatingAnswerRepository gameRatingAnswerRepository;

    public void deleteGameRatingAnswer(UUID id) {
        gameRatingAnswerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game rating answer not found"));

        gameRatingAnswerRepository.deleteById(id);
    }
}
