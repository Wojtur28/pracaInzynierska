package org.example.pracainzynierska.core.web.entrypoint;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.client.twitch.model.GameClient;
import org.example.pracainzynierska.core.client.twitch.model.GameClientApi;
import org.example.pracainzynierska.core.web.dto.GameResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TwitchController {

    private final GameClient gameClient;
    private final GameClientApi gameClientApi;

    @GetMapping("/games")
    public List<GameResponse> getGamesByTheme() {
        return gameClientApi.getGames();
    }

    @GetMapping("/{id}")
    public GameResponse getGameById(@PathVariable Long id) {
        return gameClientApi.getGameById(id);
    }
}
