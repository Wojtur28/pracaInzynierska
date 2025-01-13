package org.example.pracainzynierska.core.usecase.rating;

import com.example.model.GameRating;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingEntity;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingRepository;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.example.pracainzynierska.mapper.GameRatingMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdateGameRatingUseCase {

    private final GameRatingRepository gameRatingRepository;
    private final GameRatingMapper gameRatingMapper;
    private final UserRepository userRepository;

    public GameRating updateGameRating(UUID id, GameRating rating) {

        GameRatingEntity gameRatingEntity = gameRatingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game rating not found"));

        if (userRepository.findByEmail(rating.getUser().getEmail()) != userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())) {
            throw new IllegalArgumentException("You can't update other user's rating");
        }

        gameRatingEntity.setRating(rating.getRating());
        gameRatingEntity.setContent(rating.getContent());

        return gameRatingMapper.toDto(gameRatingRepository.save(gameRatingEntity));

    }
}
