package org.example.pracainzynierska.core.usecase.rating;

import com.example.model.GameRating;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingEntity;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PatchRatingUseCase {

    private final GameRatingRepository gameRatingRepository;
    private final VotingService votingService;

    public GameRating voteOnGameRating(UUID ratingId, String voteType) {
        GameRatingEntity rating = gameRatingRepository.findById(ratingId)
                .orElseThrow(() -> new EntityNotFoundException("Rating not found"));

        GameRatingEntity updatedRating = votingService.vote(rating, voteType, gameRatingRepository);
        return mapToGameRating(updatedRating);
    }

    private GameRating mapToGameRating(GameRatingEntity entity) {
        GameRating response = new GameRating();
        response.setId(entity.getId());
        response.setContent(entity.getContent());
        response.setRating(entity.getRating());
        response.setVotesUp(entity.getVotesUp());
        response.setVotesDown(entity.getVotesDown());
        return response;
    }
}
