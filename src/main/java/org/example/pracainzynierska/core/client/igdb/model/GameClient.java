package org.example.pracainzynierska.core.client.igdb.model;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.client.igdb.TwitchAuthService;
import org.example.pracainzynierska.core.client.igdb.TwitchConfig;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class GameClient {

    private final RestTemplate restTemplate;
    private final TwitchAuthService twitchAuthService;
    private final TwitchConfig twitchConfig;

    public String fetchGames() {
        String accessToken = twitchAuthService.getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Client-ID", twitchConfig.getClientId());
        headers.setContentType(MediaType.TEXT_PLAIN);

        String requestBody = """
                            fields name, category, themes, genres, tags, url;
                            limit 10;
                            """;

        HttpEntity<String> entity = new HttpEntity<>(requestBody,headers);
        String url = "https://api.igdb.com/v4/games";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", "YourClientID");
        headers.set("Authorization", "Bearer access_token");
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}

