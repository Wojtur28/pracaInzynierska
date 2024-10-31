package org.example.pracainzynierska.core.schedule;

import lombok.RequiredArgsConstructor;
import org.example.pracainzynierska.core.client.igdb.IGDBDataFetchService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledDataFetcher {

    private final IGDBDataFetchService igdbDataFetchService;

    @Scheduled(cron = "0 */5 * * * *")
    public void updateGameData() {
        igdbDataFetchService.fetchAndSaveGames();
    }
}
