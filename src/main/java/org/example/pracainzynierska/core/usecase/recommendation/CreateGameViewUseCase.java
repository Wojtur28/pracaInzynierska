package org.example.pracainzynierska.core.usecase.recommendation;

import com.example.model.CreateGameView;
import com.example.model.GameView;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameView.GameViewEntity;
import org.example.pracainzynierska.core.entities.gameView.GameViewRepository;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameRepository;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.example.pracainzynierska.exception.SteamGameNotFoundException;
import org.example.pracainzynierska.exception.UserNotFoundException;
import org.example.pracainzynierska.mapper.GameViewMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateGameViewUseCase {

    private final GameViewRepository gameViewRepository;
    private final GameViewMapper gameViewMapper;
    private final SteamGameRepository steamGameRepository;
    private final UserRepository userRepository;

    public GameView createGameView(UUID gameId, CreateGameView createGameView) {
        GameViewEntity gameViewEntity = gameViewMapper.toEntity(createGameView);
        gameViewEntity.setSteamGame(steamGameRepository.findById(gameId).orElseThrow(
                () -> new SteamGameNotFoundException("Steam game not found with id: " + gameId)));
        gameViewEntity.setUser(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(
                () -> new UserNotFoundException("User not found with email: " + SecurityContextHolder.getContext().getAuthentication().getName())));
        return gameViewMapper.toDto(gameViewRepository.save(gameViewEntity));
    }
}
