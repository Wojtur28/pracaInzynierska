package org.example.pracainzynierska.web.entrypoint;

import com.example.api.RecommendedGamesApi;
import com.example.model.SteamGame;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.usecase.recommendation.GetRecommendationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RecommendationController implements RecommendedGamesApi {

    private final GetRecommendationUseCase getRecommendationUseCase;

    @Override
    public ResponseEntity<List<SteamGame>> getRecommendedGames() {
        return ResponseEntity.ok(getRecommendationUseCase.getRecommendedGames());
    }
}
