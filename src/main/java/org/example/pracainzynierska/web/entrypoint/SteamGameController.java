package org.example.pracainzynierska.web.entrypoint;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.usecase.steam.GetSteamGamesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.api.SteamGamesApi;
import com.example.model.SteamGame;

import java.util.List;

@RestController
@AllArgsConstructor
public class SteamGameController implements SteamGamesApi {

    private final GetSteamGamesUseCase getSteamGamesUseCase;

    @Override
    public ResponseEntity<List<SteamGame>> getSteamGames() {
        return ResponseEntity.ok(getSteamGamesUseCase.getSteamGames());
    }

}
