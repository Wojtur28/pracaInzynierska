package org.example.pracainzynierska.core.usecase.rating;

import com.example.model.CreateGameRating;
import com.example.model.GameRating;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingEntity;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingRepository;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameRepository;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.example.pracainzynierska.mapper.GameRatingMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class CreateGameRatingUseCase {

    private final GameRatingRepository gameRatingRepository;
    private final SteamGameRepository steamGameRepository;
    private final GameRatingMapper gameRatingMapper;
    private final UserRepository userRepository;

    public GameRating createRating(UUID gameId, CreateGameRating createGameRating) {

        SteamGameEntity game = steamGameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("SteamGame not found"));

        GameRatingEntity ratingEntity = gameRatingMapper.toEntity(createGameRating);
        ratingEntity.setSteamGame(game);
        ratingEntity.setUser(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found")));

        GameRatingEntity savedRating = gameRatingRepository.save(ratingEntity);
        return gameRatingMapper.toDto(savedRating);
    }
}
