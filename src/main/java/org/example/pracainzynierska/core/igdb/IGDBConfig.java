package org.example.pracainzynierska.core.igdb;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "igdb")
@Getter
@Setter
public class IGDBConfig {

    private String clientId;
    private String clientSecret;

}
