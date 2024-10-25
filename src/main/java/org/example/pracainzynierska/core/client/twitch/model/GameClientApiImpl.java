package org.example.pracainzynierska.core.client.twitch.model;

import lombok.RequiredArgsConstructor;
import org.example.pracainzynierska.core.client.twitch.TwitchAuthService;
import org.example.pracainzynierska.core.client.twitch.TwitchConfig;
import org.example.pracainzynierska.core.web.dto.GameResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameClientApiImpl implements GameClientApi{

    private final RestTemplate restTemplate;
    private final TwitchConfig twitchConfig;
    private final TwitchAuthService twitchAuthService;
    private final String baseUrl = "https://api.igdb.com/v4";

    @Override
    public List<GameResponse> getGamesByTheme(String theme) {
        String accessToken = twitchAuthService.getAccessToken();
        HttpHeaders headers = createHeaders(accessToken);

        // Using expander to get detailed information about genres and themes
        String requestBody = "fields name,genres.*,themes.*; where themes.name = \"" + theme + "\";";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        String url = baseUrl + "/games";
        ResponseEntity<GameResponse[]> response = restTemplate.exchange(url, HttpMethod.POST, entity, GameResponse[].class);

        return List.of(response.getBody());
    }

    @Override
    public GameResponse getGameById(Long gameId) {
        String accessToken = twitchAuthService.getAccessToken();
        HttpHeaders headers = createHeaders(accessToken);

        // Using expander to retrieve all details of genres and themes
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
