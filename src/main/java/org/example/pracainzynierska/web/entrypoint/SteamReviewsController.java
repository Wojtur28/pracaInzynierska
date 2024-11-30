package org.example.pracainzynierska.web.entrypoint;

import com.example.api.SteamReviewsApi;
import com.example.model.SteamReview;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.usecase.steam.GetSteamGameReviewsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class SteamReviewsController implements SteamReviewsApi {

    private final GetSteamGameReviewsUseCase getSteamGameReviewsUseCase;

    @Override
    public ResponseEntity<List<SteamReview>> getGameReviews(@PathVariable("gameId") UUID gameId, @RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(getSteamGameReviewsUseCase.getGameReviews(gameId, page, size));
    }
}
