package org.example.pracainzynierska.core.usecase.rating;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DeleteGameRatingUseCase {

    private final GameRatingRepository gameRatingRepository;

    public void deleteGameRating(UUID id) {
        gameRatingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game rating not found"));

        gameRatingRepository.deleteById(id);
    }
}
