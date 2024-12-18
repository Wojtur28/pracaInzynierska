package org.example.pracainzynierska.core.usecase.steam;

import com.example.model.SteamGameDetail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameDetailEntity;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameDetailRepository;
import org.example.pracainzynierska.mapper.SteamGameDetailMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class GetSteamGameDetailsUseCase {

    private final SteamGameDetailRepository steamGameDetailRepository;
    private final SteamGameDetailMapper steamGameDetailMapper;


    public SteamGameDetail getSteamGameDetail(UUID steamGameId) {
        Optional<SteamGameDetailEntity> steamGameDetailEntity = steamGameDetailRepository.findBySteamGameEntity_Id(steamGameId);

        if (steamGameDetailEntity.isEmpty()) {
            log.error("Steam game detail with id {} not found", steamGameId);
            throw new RuntimeException("Steam game detail not found");
        }

        log.debug("Steam game detail: {}", steamGameDetailEntity.get());

        return steamGameDetailMapper.toDto(steamGameDetailEntity.get());
    }


}
