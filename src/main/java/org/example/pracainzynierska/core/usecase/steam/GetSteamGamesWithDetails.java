package org.example.pracainzynierska.core.usecase.steam;

import com.example.model.SteamGameWithDetails;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameRepository;
import org.example.pracainzynierska.core.specification.SteamGameSpecifications;
import org.example.pracainzynierska.mapper.SteamGameWithDetailsMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetSteamGamesWithDetails {

    private final SteamGameRepository steamGameRepository;
    private final SteamGameWithDetailsMapper steamGameWithDetailsMapper;

    public List<SteamGameWithDetails> getSteamGamesWithDetails(int page, int size, String platform, List<String> categories, List<String> genres, String search) {
        PageRequest pageRequest = PageRequest.of(page, size);

        platform = platform != null ? platform.toLowerCase() : null;

        Specification<SteamGameEntity> spec = Specification.where(SteamGameSpecifications.hasPlatform(platform))
                .and(SteamGameSpecifications.hasCategories(categories))
                .and(SteamGameSpecifications.hasGenres(genres))
                .and(SteamGameSpecifications.hasName(search));

        List<SteamGameEntity> games = steamGameRepository.findAll(spec, pageRequest).getContent();

        return games.stream()
                .map(steamGameWithDetailsMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<SteamGameWithDetails> getDetailsForGameIds(List<UUID> gameIds) {
        List<SteamGameEntity> games = steamGameRepository.findAllById(gameIds);

        return games.stream()
                .map(steamGameWithDetailsMapper::toDto)
                .collect(Collectors.toList());
    }
}
