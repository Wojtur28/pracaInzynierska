package org.example.pracainzynierska.core.usecase.steam;

import com.example.model.SteamGameWithDetails;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameRepository;
import org.example.pracainzynierska.mapper.SteamGameWithDetailsMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetSteamGamesWithDetails {

    private final SteamGameRepository steamGameRepository;
    private final SteamGameWithDetailsMapper steamGameWithDetailsMapper;

    public List<SteamGameWithDetails> getSteamGamesWithDetails(int page, int size, String platform) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<SteamGameEntity> games;

        if (platform != null && !platform.isEmpty()) {
            games = steamGameRepository.findBySteamGameDetailEntityPlatformsName(platform, pageRequest);
        } else {
            games = steamGameRepository.findAll(pageRequest).getContent();
        }

        return games.stream()
                .map(steamGameWithDetailsMapper::toDto)
                .collect(Collectors.toList());
    }
}
