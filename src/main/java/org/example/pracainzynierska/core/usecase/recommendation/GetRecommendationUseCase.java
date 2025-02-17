package org.example.pracainzynierska.core.usecase.recommendation;

import com.example.model.SteamGame;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameView.GameViewEntity;
import org.example.pracainzynierska.core.entities.gameView.GameViewRepository;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameRepository;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.example.pracainzynierska.core.utils.TagUtils;
import org.example.pracainzynierska.exception.UserNotFoundException;
import org.example.pracainzynierska.mapper.SteamGameMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetRecommendationUseCase {

    private final GameViewRepository gameViewRepository;
    private final SteamGameRepository steamGameRepository;
    private final SteamGameMapper steamGameMapper;
    private final UserRepository userRepository;

    public List<SteamGame> getRecommendedGames() {
        UserEntity user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Set<String> userTags = gameViewRepository.findByUserIdAndViewDurationGreaterThan(user.getId(), 5)
                .stream()
                .map(GameViewEntity::getSteamGame)
                .filter(Objects::nonNull)
                .filter(game -> game.getTags() != null && !game.getTags().isEmpty())
                .flatMap(game -> TagUtils.splitTags(game.getTags()).stream())
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        List<SteamGameEntity> allGames = steamGameRepository.findAll()
                .stream()
                .filter(game -> game.getTags() != null && !game.getTags().isEmpty())
                .toList();

        List<ScoredGame> scoredGames = allGames.stream().map(game -> {
            Set<String> gameTags = TagUtils.splitTags(game.getTags())
                    .stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());
            Set<String> intersection = new HashSet<>(userTags);
            intersection.retainAll(gameTags);
            Set<String> union = new HashSet<>(userTags);
            union.addAll(gameTags);
            double score = union.isEmpty() ? 0 : ((double) intersection.size()) / union.size();
            return new ScoredGame(game, score);
        }).sorted((a, b) -> Double.compare(b.score, a.score)).toList();

        List<SteamGameEntity> topGames = scoredGames.stream()
                .filter(sg -> sg.score > 0)
                .limit(12)
                .map(sg -> sg.game)
                .collect(Collectors.toList());
        return steamGameMapper.toDto(topGames);
    }

    private record ScoredGame(SteamGameEntity game, double score) {
    }

}
