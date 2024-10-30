package org.example.pracainzynierska.core.client.twitch.model;

import lombok.RequiredArgsConstructor;
import org.example.pracainzynierska.core.client.twitch.TwitchAuthService;
import org.example.pracainzynierska.core.client.twitch.TwitchConfig;
import org.example.pracainzynierska.core.web.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class GameClientApiImpl implements GameClientApi{

    private final RestTemplate restTemplate;
    private final TwitchConfig twitchConfig;
    private final TwitchAuthService twitchAuthService;
    private final String baseUrl = "https://api.igdb.com/v4";
    private static final Logger logger = LoggerFactory.getLogger(GameClientApiImpl.class);


    @Override
    public List<GameResponse> getGames() {
        // Step 1: Obtain Access Token
        String accessToken = twitchAuthService.getAccessToken();
        HttpHeaders headers = createHeaders(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Step 2: Construct Multi-Query Request Body
        String requestBody = """
            query games "Games with Genres and Themes" {
                fields name, category, themes.name, genres.name, screenshots, rating, platforms.name;
                where category = 0 & themes != null & genres != null;
                limit 50;
            };
            """;

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // Step 3: Make the Multi-Query Request
        String url = baseUrl + "/multiquery";
        ResponseEntity<MultiQueryResponse[]> response;

        try {
            response = restTemplate.exchange(url, HttpMethod.POST, entity, MultiQueryResponse[].class);
        } catch (HttpClientErrorException e) {
            // Handle 4xx errors
            logger.error("Client error while fetching games: {}", e.getStatusCode());
            throw new RuntimeException("Failed to fetch games due to client error.", e);
        } catch (HttpServerErrorException e) {
            // Handle 5xx errors
            logger.error("Server error while fetching games: {}", e.getStatusCode());
            throw new RuntimeException("Failed to fetch games due to server error.", e);
        } catch (Exception e) {
            // Handle other errors
            logger.error("Unexpected error while fetching games: {}", e.getMessage());
            throw new RuntimeException("An unexpected error occurred while fetching games.", e);
        }

        // Step 4: Handle the Response
        if (response.getBody() == null || response.getBody().length == 0) {
            logger.warn("No data received from the recommendation service.");
            return Collections.emptyList();
        }

        // Extract the specific query result
        MultiQueryResponse multiQueryResponse = Arrays.stream(response.getBody())
                .filter(r -> "Games with Genres and Themes".equals(r.name()))
                .findFirst()
                .orElse(null);

        if (multiQueryResponse == null || multiQueryResponse.result() == null) {
            logger.warn("No results found for the specified query.");
            return Collections.emptyList();
        }

        // Step 5: Map to GameResponse Objects
        List<GameResponse> games = multiQueryResponse.result().stream().map(gameData -> {
            List<String> platforms = Optional.ofNullable(gameData.platforms())
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(Platform::name)
                    .collect(Collectors.toList());

            List<String> genres = Optional.ofNullable(gameData.genres())
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(Genre::name)
                    .collect(Collectors.toList());

            List<String> themes = Optional.ofNullable(gameData.themes())
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(Theme::name)
                    .collect(Collectors.toList());

            return new GameResponse(
                    gameData.name(),
                    gameData.category(),
                    genres,
                    themes,
                    gameData.screenshots(),
                    gameData.rating(),
                    platforms
            );
        }).collect(Collectors.toList());

        return games;
    }

    @Override
    public GameResponse getGameById(Long gameId) {
        String accessToken = twitchAuthService.getAccessToken();
        HttpHeaders headers = createHeaders(accessToken);

        String requestBody = "fields name,genres.*,themes.*; where id = " + gameId + ";";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        String url = baseUrl + "/games";
        ResponseEntity<GameResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, GameResponse.class);

        return response.getBody();
    }

    private HttpHeaders createHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Client-ID", twitchConfig.getClientId());
        headers.setContentType(MediaType.TEXT_PLAIN);
        return headers;
    }
}
