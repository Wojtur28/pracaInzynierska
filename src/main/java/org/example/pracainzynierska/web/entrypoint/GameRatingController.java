package org.example.pracainzynierska.web.entrypoint;

import com.example.api.GameRatingsApi;
import com.example.model.CreateGameRating;
import com.example.model.GameRating;
import com.example.model.VoteRequest;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.usecase.rating.CreateGameRatingUseCase;
import org.example.pracainzynierska.core.usecase.rating.GetGameRatingsUseCase;
import org.example.pracainzynierska.core.usecase.rating.PatchRatingUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class GameRatingController implements GameRatingsApi {

    private final GetGameRatingsUseCase getGameRatingsUseCase;
    private final CreateGameRatingUseCase createGameRatingUseCase;
    private final PatchRatingUseCase patchRatingUseCase;

    @Override
    public ResponseEntity<List<GameRating>> getGameRatings(
            @PathVariable("gameId") UUID gameId,
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sort) {
        return ResponseEntity.ok(getGameRatingsUseCase.getGameRatings(gameId, page, size, sort));
    }

    @Override
    public ResponseEntity<GameRating> createGameRating(
            @PathVariable("gameId") UUID gameId,
            @RequestBody CreateGameRating createGameRating) {
        return ResponseEntity.ok(createGameRatingUseCase.createRating(gameId, createGameRating));
    }

    @Override
    public ResponseEntity<GameRating> voteOnGameRating(
            @PathVariable("ratingId") UUID ratingId,
            @RequestBody VoteRequest voteRequest) {
        GameRating updatedRating = patchRatingUseCase.voteOnGameRating(ratingId, voteRequest.getVoteType().toString());
        return ResponseEntity.ok(updatedRating);
    }

}
