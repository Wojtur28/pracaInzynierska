package org.example.pracainzynierska.core.igdb;

import lombok.RequiredArgsConstructor;
import org.example.pracainzynierska.core.entities.game.GameEntity;
import org.example.pracainzynierska.core.entities.game.GameRepository;
import org.example.pracainzynierska.core.entities.genre.GenreEntity;
import org.example.pracainzynierska.core.entities.genre.GenreRepository;
import org.example.pracainzynierska.core.entities.platform.PlatformEntity;
import org.example.pracainzynierska.core.entities.platform.PlatformRepository;
import org.example.pracainzynierska.core.entities.screenshot.ScreenshotEntity;
import org.example.pracainzynierska.core.entities.screenshot.ScreenshotRepository;
import org.example.pracainzynierska.core.entities.theme.ThemeEntity;
import org.example.pracainzynierska.core.entities.theme.ThemeRepository;
import org.example.pracainzynierska.core.mapper.GameMapper;
import org.example.pracainzynierska.core.web.dto.Game;
import org.example.pracainzynierska.core.web.dto.MultiQueryGameCountResponse;
import org.example.pracainzynierska.core.web.dto.MultiQueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IGDBDataFetchService {

    private final RestTemplate restTemplate;
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final IGDBAuthService IGDBAuthService;
    private final IGDBConfig IGDBConfig;
    private final GenreRepository genreRepository;
    private final ThemeRepository themeRepository;
    private final ScreenshotRepository screenshotRepository;
    private final PlatformRepository platformRepository;

    private static final Logger logger = LoggerFactory.getLogger(IGDBDataFetchService.class);
    private static final String IGDB_BASE_URL = "https://api.igdb.com/v4";
    private static final String MULTIQUERY_ENDPOINT = "/multiquery";

    public void fetchAndSaveGames() {
        String accessToken = IGDBAuthService.getAccessToken();
        HttpHeaders headers = createHeaders(accessToken);

        String countRequestBody = """
        query games/count "Count of Games" {
            fields rating, themes, genres;
            where category = 0 & themes != null & genres != null & themes != 42 & rating != 0;
        };
        """;

        HttpEntity<String> countEntity = new HttpEntity<>(countRequestBody, headers);

        try {
            ResponseEntity<MultiQueryGameCountResponse[]> countResponse = restTemplate.exchange(
                    IGDB_BASE_URL + MULTIQUERY_ENDPOINT,
                    HttpMethod.POST,
                    countEntity,
                    MultiQueryGameCountResponse[].class
            );

            if (countResponse.getBody() != null && countResponse.getBody().length > 0) {
                MultiQueryGameCountResponse countResult = countResponse.getBody()[0];
                int totalCount = countResult.count();
                logger.info("Total games count: {}", totalCount);

                int limit = 500;
                int iterations = (int) Math.ceil((double) totalCount / limit);

                for (int i = 0; i < iterations; i++) {
                    int offset = i * limit;

                    String gamesRequestBody = String.format("""
                    query games "Games with Expanded Fields" {
                        fields id,
                               name,
                               genres.name,
                               themes.name,
                               screenshots.image_id,
                               rating,
                               platforms.name;
                        where category = 0 & themes != null & genres != null & themes != 42 & rating != 0;
                        limit %d;
                        offset %d;
                    };
                    """, limit, offset);

                    HttpEntity<String> gamesEntity = new HttpEntity<>(gamesRequestBody, headers);

                    ResponseEntity<MultiQueryResponse[]> gamesResponse = restTemplate.exchange(
                            IGDB_BASE_URL + MULTIQUERY_ENDPOINT,
                            HttpMethod.POST,
                            gamesEntity,
                            MultiQueryResponse[].class
                    );

                    if (gamesResponse.getBody() != null && gamesResponse.getBody().length > 0) {
                        MultiQueryResponse gamesResult = gamesResponse.getBody()[0];
                        Set<Game> gameRespons = gamesResult.result();

                        for (Game game : gameRespons) {
                            saveOrUpdateGame(game);
                        }
                    } else {
                        logger.warn("No data received from IGDB API at offset {}", offset);
                        logger.warn("Response status: {}", gamesResponse.getStatusCode());
                        logger.warn("Response body: {}", gamesResponse.getBody());
                    }
                }
            } else {
                logger.warn("No data received from IGDB API for count query.");
                logger.warn("Response status: {}", countResponse.getStatusCode());
                logger.warn("Response body: {}", countResponse.getBody());
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.error("HTTP error when calling IGDB API: {}", e.getMessage(), e);

        } catch (Exception e) {
            logger.error("Error when calling IGDB API: {}", e.getMessage(), e);
        }
    }

    @Transactional
    public void saveOrUpdateGame(Game game) {

        Optional<GameEntity> existingGameOpt = gameRepository.findByApiId(game.id());

        GameEntity gameEntity;
        if (existingGameOpt.isPresent()) {
            gameEntity = existingGameOpt.get();
            gameEntity.setName(game.name());
            gameEntity.setApiRating(game.rating() != null ? game.rating() : 0.0);
        } else {
            gameEntity = gameMapper.toEntity(game);
            gameEntity.setApiId(game.id());
        }

        Set<GenreEntity> linkedGenres = gameEntity.getGenres().stream()
                .map(genre -> {
                    Optional<GenreEntity> existingGenreOpt = genreRepository.findByApiId(genre.getApiId());
                    if (existingGenreOpt.isPresent()) {
                        return existingGenreOpt.get();
                    } else {
                        return genreRepository.save(genre);
                    }
                })
                .collect(Collectors.toSet());
        gameEntity.setGenres(linkedGenres);

        Set<ThemeEntity> linkedThemes = gameEntity.getThemes().stream()
                .map(theme -> {
                    Optional<ThemeEntity> existingThemeOpt = themeRepository.findByApiId(theme.getApiId());
                    if (existingThemeOpt.isPresent()) {
                        return existingThemeOpt.get();
                    } else {
                        return themeRepository.save(theme);
                    }
                })
                .collect(Collectors.toSet());
        gameEntity.setThemes(linkedThemes);

        Set<PlatformEntity> linkedPlatforms = gameEntity.getPlatforms().stream()
                .map(platform -> {
                    Optional<PlatformEntity> existingPlatformOpt = platformRepository.findByApiId(platform.getApiId());
                    if (existingPlatformOpt.isPresent()) {
                        return existingPlatformOpt.get();
                    } else {
                        return platformRepository.save(platform);
                    }
                })
                .collect(Collectors.toSet());
        gameEntity.setPlatforms(linkedPlatforms);

        Set<ScreenshotEntity> linkedScreenshots = Optional.ofNullable(gameEntity.getScreenshots())
                .orElse(Collections.emptySet())
                .stream()
                .map(screenshot -> {
                    Optional<ScreenshotEntity> existingScreenshotOpt = screenshotRepository.findByApiId(screenshot.getApiId());
                    if (existingScreenshotOpt.isPresent()) {
                        ScreenshotEntity existingScreenshot = existingScreenshotOpt.get();
                        existingScreenshot.setGame(gameEntity);
                        return existingScreenshot;
                    } else {
                        screenshot.setGame(gameEntity);
                        return screenshot;
                    }
                })
                .collect(Collectors.toSet());
        gameEntity.setScreenshots(linkedScreenshots);

        gameRepository.save(gameEntity);
    }

    private HttpHeaders createHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.set("Client-ID", IGDBConfig.getClientId());
        headers.setContentType(MediaType.TEXT_PLAIN);
        return headers;
    }
}
