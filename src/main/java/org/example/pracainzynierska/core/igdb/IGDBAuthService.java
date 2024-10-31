package org.example.pracainzynierska.core.igdb;

import lombok.RequiredArgsConstructor;
import org.example.pracainzynierska.core.web.dto.TokenResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class IGDBAuthService {

    private final IGDBConfig IGDBConfig;
    private final RestTemplate restTemplate;
    private TokenResponse tokenResponse;

    public String getAccessToken() {
        if (tokenResponse == null || isTokenExpired()) {
            refreshToken();
        }
        return tokenResponse.accessToken();
    }

    private void refreshToken() {
        String url = "https://id.twitch.tv/oauth2/token?client_id=" + IGDBConfig.getClientId() +
                "&client_secret=" + IGDBConfig.getClientSecret() +
                "&grant_type=client_credentials";

        ResponseEntity<Map> response = restTemplate.exchange(
                url, HttpMethod.POST, new HttpEntity<>(new HttpHeaders()), Map.class);

        Map<String, Object> body = response.getBody();
        if (body != null && body.containsKey("access_token")) {
            this.tokenResponse = new TokenResponse(
                    (String) body.get("access_token"),
                    (int) body.get("expires_in")
            );
        }
    }

    private boolean isTokenExpired() {
        return tokenResponse != null && tokenResponse.expiresIn() <= 0;
    }
}
