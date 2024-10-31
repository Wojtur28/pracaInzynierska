package org.example.pracainzynierska.core.entities.game;

import lombok.RequiredArgsConstructor;
import org.example.pracainzynierska.core.mapper.GameMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

}
