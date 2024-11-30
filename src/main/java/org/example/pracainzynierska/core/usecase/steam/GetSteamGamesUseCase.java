package org.example.pracainzynierska.core.usecase.steam;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameRepository;
import org.example.pracainzynierska.mapper.SteamGameMapper;
import org.springframework.stereotype.Service;
import com.example.model.SteamGame;
import java.util.List;

@Service
@AllArgsConstructor
public class GetSteamGamesUseCase {

    private final SteamGameRepository steamGameRepository;
    private final SteamGameMapper steamGameMapper;

    public List<SteamGame> getSteamGames() {
        return steamGameMapper.toModel(steamGameRepository.findAll());
    }
}
