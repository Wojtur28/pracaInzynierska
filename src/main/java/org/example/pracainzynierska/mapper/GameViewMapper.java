package org.example.pracainzynierska.mapper;

import com.example.model.CreateGameView;
import com.example.model.GameView;
import org.example.pracainzynierska.core.entities.gameView.GameViewEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {DateTimeMapper.class})
public interface GameViewMapper {

    GameViewEntity toEntity(GameView gameView);

    GameView toDto(GameViewEntity gameViewEntity);

    GameViewEntity toEntity(CreateGameView createGameView);
}
