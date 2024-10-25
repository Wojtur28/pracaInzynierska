package org.example.pracainzynierska.core.web.entrypoint;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.client.twitch.model.GameClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TwitchController {

    private final GameClient gameClient;

    @GetMapping("/games")
    public String getGames() {
        return gameClient.fetchGames();
    }
}
