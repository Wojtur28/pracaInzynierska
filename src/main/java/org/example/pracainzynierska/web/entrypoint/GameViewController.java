package org.example.pracainzynierska.web.entrypoint;

import com.example.api.GameViewsApi;
import com.example.model.CreateGameView;
import com.example.model.GameView;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.usecase.recommendation.CreateGameViewUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class GameViewController implements GameViewsApi {

    private final CreateGameViewUseCase createGameViewUseCase;

    @Override
    public ResponseEntity<GameView> createGameView(@PathVariable("gameId") UUID gameId, @RequestBody CreateGameView createGameView) {
        return ResponseEntity.ok(createGameViewUseCase.createGameView(gameId, createGameView));
    }

}
