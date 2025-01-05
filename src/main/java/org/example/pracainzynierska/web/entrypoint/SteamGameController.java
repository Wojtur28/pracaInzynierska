package org.example.pracainzynierska.web.entrypoint;

import com.example.model.SteamGameDetail;
import com.example.model.SteamGameWithDetails;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.usecase.steam.GetSteamGameDetailsUseCase;
import org.example.pracainzynierska.core.usecase.steam.GetSteamGameUseCase;
import org.example.pracainzynierska.core.usecase.steam.GetSteamGamesUseCase;
import org.example.pracainzynierska.core.usecase.steam.GetSteamGamesWithDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.api.SteamGamesApi;
import com.example.model.SteamGame;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class SteamGameController implements SteamGamesApi {

    private final GetSteamGamesUseCase getSteamGamesUseCase;
    private final GetSteamGameDetailsUseCase getSteamGameDetailsUseCase;
    private final GetSteamGamesWithDetails getSteamGamesWithDetails;
    private final GetSteamGameUseCase getSteamGameUseCase;

    @Override
    public ResponseEntity<List<SteamGame>> getSteamGames(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(getSteamGamesUseCase.getSteamGames(page, size));
    }

    @Override
    public ResponseEntity<SteamGameDetail> getSteamGameDetail(@PathVariable("gameId") UUID gameId) {
        return ResponseEntity.ok(getSteamGameDetailsUseCase.getSteamGameDetail(gameId));
    }

    @Override
    public ResponseEntity<List<SteamGameWithDetails>> getSteamGamesWithDetails(@RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) String platform, @RequestParam(required = false) List<String> categories, @RequestParam(required = false) List<String> genres, @RequestParam(required = false) String search) {
        return ResponseEntity.ok(getSteamGamesWithDetails.getSteamGamesWithDetails(page, size, platform, categories, genres, search));
    }

    @Override
    public ResponseEntity<SteamGame> getSteamGame(@PathVariable("gameId") UUID gameId) {
        return ResponseEntity.ok(getSteamGameUseCase.getSteamGame(gameId));
    }

}
