package org.example.pracainzynierska.core.usecase.steam;

import com.example.model.SteamGame;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameRepository;
import org.example.pracainzynierska.mapper.SteamGameMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GetSteamGameUseCase {

    private final SteamGameRepository steamGameRepository;
    private final SteamGameMapper steamGameMapper;

    public SteamGame getSteamGame(UUID steamGameId) {
        return steamGameRepository.findById(UUID.fromString(String.valueOf(steamGameId)))
                .map(steamGameMapper::toDto)
                .orElseThrow(
                        () -> new RuntimeException("Steam game not found")
                );
    }
}
