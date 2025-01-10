package org.example.pracainzynierska.web.entrypoint;

import com.example.api.GameRatingAnswersApi;
import com.example.model.CreateGameRatingAnswer;
import com.example.model.GameRatingAnswer;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.usecase.rating.CreateGameRatingAnswerUseCase;
import org.example.pracainzynierska.core.usecase.rating.GetGameRatingAnswerUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class GameRatingAnswerController implements GameRatingAnswersApi {

    private final GetGameRatingAnswerUseCase getGameRatingAnswerUseCase;
    private final CreateGameRatingAnswerUseCase createGameRatingAnswerUseCase;

    @Override
    public ResponseEntity<List<GameRatingAnswer>> getGameRatingAnswers(
            @PathVariable("ratingId") UUID ratingId) {
        return ResponseEntity.ok(getGameRatingAnswerUseCase.getAnswersForRating(ratingId));
    }

    @Override
    public ResponseEntity<GameRatingAnswer> createGameRatingAnswer(@PathVariable("ratingId") UUID ratingId, CreateGameRatingAnswer createGameRatingAnswer) {
        createGameRatingAnswerUseCase.createAnswer(ratingId, createGameRatingAnswer);
        return ResponseEntity.ok().build();
    }

}
