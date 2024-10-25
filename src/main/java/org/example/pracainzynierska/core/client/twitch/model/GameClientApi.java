package org.example.pracainzynierska.core.client.twitch.model;

import org.example.pracainzynierska.core.web.dto.GameResponse;

import java.util.List;

public interface GameClientApi {

    List<GameResponse> getGamesByTheme(String theme);
    GameResponse getGameById(Long gameId);

}
