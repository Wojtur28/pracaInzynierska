package org.example.pracainzynierska.mapper;

import com.example.model.CreateGameView;
import com.example.model.GameView;
import org.example.pracainzynierska.core.entities.gameView.GameViewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {DateTimeMapper.class})
public interface GameViewMapper {

    GameViewEntity toEntity(GameView gameView);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "steamGameId", source = "steamGame.id")
    GameView toDto(GameViewEntity gameViewEntity);

    GameViewEntity toEntity(CreateGameView createGameView);
}
