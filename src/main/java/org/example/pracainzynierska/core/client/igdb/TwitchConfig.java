package org.example.pracainzynierska.core.client.igdb;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twitch")
@Getter
@Setter
public class TwitchConfig {

    private String clientId;
    private String clientSecret;

}
