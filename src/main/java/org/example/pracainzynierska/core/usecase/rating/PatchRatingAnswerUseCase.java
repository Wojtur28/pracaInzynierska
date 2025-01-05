package org.example.pracainzynierska.core.usecase.rating;

import com.example.model.GameRatingAnswer;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingAnswerEntity;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PatchRatingAnswerUseCase {

    private final GameRatingAnswerRepository gameRatingAnswerRepository;
    private final VotingService votingService;

    public GameRatingAnswer voteOnGameRatingAnswer(UUID answerId, String voteType) {
        GameRatingAnswerEntity answer = gameRatingAnswerRepository.findById(answerId)
                .orElseThrow(() -> new EntityNotFoundException("Answer not found"));

        GameRatingAnswerEntity updatedAnswer = votingService.vote(answer, voteType, gameRatingAnswerRepository);
        return mapToGameRatingAnswer(updatedAnswer);
    }

    private GameRatingAnswer mapToGameRatingAnswer(GameRatingAnswerEntity entity) {
        GameRatingAnswer response = new GameRatingAnswer();
        response.setId(entity.getId());
        response.setContent(entity.getContent());
        response.setVotesUp(entity.getVotesUp());
        response.setVotesDown(entity.getVotesDown());
        return response;
    }


    }
