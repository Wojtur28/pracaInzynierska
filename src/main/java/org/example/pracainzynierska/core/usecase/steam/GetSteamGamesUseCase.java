package org.example.pracainzynierska.core.usecase.steam;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameRepository;
import org.example.pracainzynierska.mapper.SteamGameMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.model.SteamGame;
import java.util.List;

@Service
@AllArgsConstructor
public class GetSteamGamesUseCase {

    private final SteamGameRepository steamGameRepository;
    private final SteamGameMapper steamGameMapper;

    public List<SteamGame> getSteamGames(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SteamGame> steamGames = steamGameRepository.findAll(pageable)
                .map(steamGameMapper::toModel);

        return steamGames.getContent();
    }
}
