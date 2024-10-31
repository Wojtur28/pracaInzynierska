package org.example.pracainzynierska.core.client.igdb;

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
import org.example.pracainzynierska.core.web.dto.GameResponse;
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
    private final TwitchAuthService twitchAuthService;
    private final TwitchConfig twitchConfig;
    private final GenreRepository genreRepository;
    private final ThemeRepository themeRepository;
    private final ScreenshotRepository screenshotRepository;
    private final PlatformRepository platformRepository;

    private static final Logger logger = LoggerFactory.getLogger(IGDBDataFetchService.class);
    private static final String IGDB_BASE_URL = "https://api.igdb.com/v4";
    private static final String MULTIQUERY_ENDPOINT = "/multiquery";

    public void fetchAndSaveGames() {
        String accessToken = twitchAuthService.getAccessToken();
        HttpHeaders headers = createHeaders(accessToken);

        String requestBody = """
            query games "Games with Expanded Fields" {
                fields id,
                       name,
                       genres.name,
                       themes.name,
                       screenshots.image_id,
                       rating,
                       platforms.name;
                where themes != null & genres != null & themes != 42;
                limit 500;
            };
            """;

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<MultiQueryResponse[]> response = restTemplate.exchange(
                    IGDB_BASE_URL + MULTIQUERY_ENDPOINT,
                    HttpMethod.POST,
                    entity,
                    MultiQueryResponse[].class
            );

            if (response.getBody() != null && response.getBody().length > 0) {
                MultiQueryResponse multiQueryResponse = response.getBody()[0];
                List<GameResponse> gameResponses = multiQueryResponse.result();

                for (GameResponse gameResponse : gameResponses) {
                    saveOrUpdateGame(gameResponse);
                }
            } else {
                logger.warn("No data received from IGDB API.");
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Handle HTTP errors
            logger.error("HTTP error when calling IGDB API: {}", e.getMessage(), e);
            // Handle specific status codes if needed
        } catch (Exception e) {
            // Handle other exceptions
            logger.error("Error when calling IGDB API: {}", e.getMessage(), e);
        }
    }

    @Transactional
    public void saveOrUpdateGame(GameResponse gameResponse) {
        // Map GameResponse to GameEntity
        GameEntity gameEntity = gameMapper.toEntity(gameResponse);

        // Check if the game already exists in the database
        Optional<GameEntity> existingGameOpt = gameRepository.findByApiId(gameResponse.id());

        if (existingGameOpt.isPresent()) {
            // If the game exists, set the ID to ensure we're updating the existing record
            GameEntity existingGame = existingGameOpt.get();
            gameEntity.setId(existingGame.getId());
        }

        if (gameEntity.getPlatforms() == null) {
            gameEntity.setPlatforms(new HashSet<>());
        }
        if (gameEntity.getGenres() == null) {
            gameEntity.setGenres(new HashSet<>());
        }
        if (gameEntity.getThemes() == null) {
            gameEntity.setThemes(new HashSet<>());
        }
        if (gameEntity.getScreenshots() == null) {
            gameEntity.setScreenshots(new HashSet<>());
        }

        // Process Genres
        Set<GenreEntity> linkedGenres = gameEntity.getGenres().stream()
                .map(genre -> {
                    Optional<GenreEntity> existingGenreOpt = genreRepository.findByApiId(genre.getApiId());
                    if (existingGenreOpt.isPresent()) {
                        return existingGenreOpt.get(); // Managed entity
                    } else {
                        return genreRepository.save(genre); // Persist new genre
                    }
                })
                .collect(Collectors.toSet());
        gameEntity.setGenres(linkedGenres);

        // Process Themes
        Set<ThemeEntity> linkedThemes = gameEntity.getThemes().stream()
                .map(theme -> {
                    Optional<ThemeEntity> existingThemeOpt = themeRepository.findByApiId(theme.getApiId());
                    if (existingThemeOpt.isPresent()) {
                        return existingThemeOpt.get(); // Managed entity
                    } else {
                        return themeRepository.save(theme); // Persist new theme
                    }
                })
                .collect(Collectors.toSet());
        gameEntity.setThemes(linkedThemes);

        // Process Platforms
        Set<PlatformEntity> linkedPlatforms = gameEntity.getPlatforms().stream()
                .map(platform -> {
                    Optional<PlatformEntity> existingPlatformOpt = platformRepository.findByApiId(platform.getApiId());
                    if (existingPlatformOpt.isPresent()) {
                        return existingPlatformOpt.get(); // Managed entity
                    } else {
                        return platformRepository.save(platform); // Persist new platform
                    }
                })
                .collect(Collectors.toSet());
        gameEntity.setPlatforms(linkedPlatforms);

        // Process Screenshots (keep cascading if appropriate)
        Set<ScreenshotEntity> linkedScreenshots = Optional.ofNullable(gameEntity.getScreenshots())
                .orElse(Collections.emptySet())
                .stream()
                .map(screenshot -> {
                    Optional<ScreenshotEntity> existingScreenshotOpt = screenshotRepository.findByApiId(screenshot.getApiId());
                    if (existingScreenshotOpt.isPresent()) {
                        ScreenshotEntity existingScreenshot = existingScreenshotOpt.get();
                        existingScreenshot.setGame(gameEntity); // Update reference
                        return existingScreenshot;
                    } else {
                        screenshot.setGame(gameEntity); // Set reference for new screenshot
                        return screenshot;
                    }
                })
                .collect(Collectors.toSet());
        gameEntity.setScreenshots(linkedScreenshots);


        // Save the game entity to the database
        gameRepository.save(gameEntity);
    }

    private HttpHeaders createHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.set("Client-ID", twitchConfig.getClientId());
        headers.setContentType(MediaType.TEXT_PLAIN);
        return headers;
    }
}
