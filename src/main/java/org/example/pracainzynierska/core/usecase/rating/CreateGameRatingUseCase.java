package org.example.pracainzynierska.core.usecase.rating;

import com.example.model.CreateGameRating;
import com.example.model.GameRating;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingEntity;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingRepository;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameRepository;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.example.pracainzynierska.mapper.GameRatingMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

        GameRatingEntity ratingEntity = new GameRatingEntity();
        ratingEntity.setSteamGame(game);
        ratingEntity.setRating(createGameRating.getRating());
        ratingEntity.setContent(createGameRating.getContent());
        ratingEntity.setVotesUp(0);
        ratingEntity.setVotesDown(0);

        //TODO: get user from security context and delete hardcoded user!!!
        ratingEntity.setUser(userRepository.findById(UUID.fromString("b90f2aa8-45ff-44bb-b75b-f2d7dff95486"))
                .orElseThrow(() -> new EntityNotFoundException("User not found")));

        GameRatingEntity savedRating = gameRatingRepository.save(ratingEntity);
        return gameRatingMapper.toModel(savedRating);
    }
}
