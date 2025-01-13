package org.example.pracainzynierska.core.usecase.rating;

import com.example.model.GameRatingAnswer;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingAnswerEntity;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingAnswerRepository;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.example.pracainzynierska.mapper.GameRatingAnswerMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdateGameRatingAnswerUseCase {

    private final GameRatingAnswerRepository gameRatingAnswerRepository;
    private final GameRatingAnswerMapper gameRatingAnswerMapper;
    private final UserRepository userRepository;

    public GameRatingAnswer updateGameRatingAnswer(UUID id, GameRatingAnswer rating) {

        GameRatingAnswerEntity gameRatingAnswerEntity = gameRatingAnswerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game rating answer not found"));

        String loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity loggedInUser = userRepository.findByEmail(loggedInUserEmail)
                .orElseThrow(() -> new IllegalArgumentException("Logged-in user not found"));

        if (!gameRatingAnswerEntity.getCreatedBy().getId().equals(loggedInUser.getId())) {
            throw new IllegalArgumentException("You can't update other user's rating");
        }

        gameRatingAnswerEntity.setContent(rating.getContent());

        return gameRatingAnswerMapper.toDto(gameRatingAnswerRepository.save(gameRatingAnswerEntity));

    }
}
