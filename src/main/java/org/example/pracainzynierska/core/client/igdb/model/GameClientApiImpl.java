/*
package org.example.pracainzynierska.core.client.igdb.model;

import lombok.RequiredArgsConstructor;
import org.example.pracainzynierska.core.exception.GameFetchException;
import org.example.pracainzynierska.core.client.igdb.TwitchAuthService;
import org.example.pracainzynierska.core.client.igdb.TwitchConfig;
import org.example.pracainzynierska.core.entities.game.GameEntity;
import org.example.pracainzynierska.core.entities.game.GameRepository;
import org.example.pracainzynierska.core.mapper.GameMapper;
import org.example.pracainzynierska.core.web.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameClientApiImpl implements GameClientApi {

    private final RestTemplate restTemplate;
    private final TwitchConfig twitchConfig;
    private final TwitchAuthService twitchAuthService;
    private final GameMapper gameMapper;
    private final GameRepository gameRepository;
    private static final Logger logger = LoggerFactory.getLogger(GameClientApiImpl.class);

    private final String baseUrl = "https://api.igdb.com/v4";
    private final String queryName = "Games with Genres and Themes";
    private final int limit = 25;

    @Override
    @Cacheable(value = "gamesCache")
    public List<GameResponse> getGames() {
        // Step 1: Obtain Access Token
        String accessToken = twitchAuthService.getAccessToken();
        HttpHeaders headers = createHeaders(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Step 2: Construct Multi-Query Request Body
        String requestBody = String.format("""
            query games "%s" {
                fields name, genres.name, themes.name, screenshots, rating, platforms.name;
                where themes != null & genres != null;
                limit %d;
            };
            """, queryName, limit);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // Step 3: Make the Multi-Query Request
        ResponseEntity<MultiQueryResponse[]> response;
        try {
            response = restTemplate.exchange(baseUrl + "/multiquery", HttpMethod.POST, entity, MultiQueryResponse[].class);
        } catch (HttpClientErrorException e) {
            // Handle 4xx errors
            logger.error("Client error while fetching games: {}", e.getStatusCode());
            throw new GameFetchException("Failed to fetch games due to client error.", e);
        } catch (HttpServerErrorException e) {
            // Handle 5xx errors
            logger.error("Server error while fetching games: {}", e.getStatusCode());
            throw new GameFetchException("Failed to fetch games due to server error.", e);
        } catch (Exception e) {
            // Handle other errors
            logger.error("Unexpected error while fetching games: {}", e.getMessage());
            throw new GameFetchException("An unexpected error occurred while fetching games.", e);
        }

        // Step 4: Handle the Response
        if (response.getBody() == null || response.getBody().length == 0) {
            logger.warn("No data received from the recommendation service.");
            return Collections.emptyList();
        }

        // Extract the specific query result
        Optional<MultiQueryResponse> optionalResponse = Arrays.stream(response.getBody())
                .filter(r -> queryName.equals(r.name()))
                .findFirst();

        if (optionalResponse.isEmpty() || optionalResponse.get().result() == null) {
            logger.warn("No results found for the specified query.");
            return Collections.emptyList();
        }

        MultiQueryResponse multiQueryResponse = optionalResponse.get();

        // Step 5: Map to GameEntity and persist
        List<GameResponse> games = multiQueryResponse.result().stream().map(gameResponse -> {
            // Check if the game already exists to prevent duplicates
            Optional<GameEntity> existingGame = gameRepository.findByApiId(gameResponse.id());
            if (existingGame.isPresent()) {
                logger.info("Game with apiId {} already exists. Skipping.", gameResponse.id());
                return gameMapper.toDto(existingGame.get());
            }

            // Map GameResponse to GameEntity using MapStruct
            GameEntity gameEntity = gameMapper.toEntity(gameResponse);

            // Persist the GameEntity
            GameEntity savedGame = gameRepository.save(gameEntity);

            // Convert the saved GameEntity back to GameResponse for return
            return gameMapper.toDto(savedGame);
        }).collect(Collectors.toList());

        logger.info("Fetched and persisted {} games successfully.", games.size());

        return games;
    }

*/
/*    @Override
    @Async
    public CompletableFuture<List<GameResponse>> getGamesAsync() {
        List<GameResponse> games = getGames();
        return CompletableFuture.completedFuture(games);
    }*//*


    // Helper method to create headers with the access token
    private HttpHeaders createHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Client-ID", twitchConfig.getClientId());
        return headers;
    }
}
*/
