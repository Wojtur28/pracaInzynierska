package org.example.pracainzynierska.core.client.igdb.model;

import org.example.pracainzynierska.core.web.dto.GameResponse;

import java.util.List;

public interface GameClientApi {

    List<GameResponse> getGames();

    //CompletableFuture<List<GameResponse>> getGamesAsync();

}
