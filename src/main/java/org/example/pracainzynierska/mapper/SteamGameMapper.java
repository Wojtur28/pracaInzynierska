package org.example.pracainzynierska.mapper;

import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;
import org.mapstruct.Mapper;
import com.example.model.SteamGame;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {BaseMapper.class, SteamReviewMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SteamGameMapper {

    SteamGame toModel(SteamGameEntity steamGameEntity);

    SteamGameEntity toEntity(SteamGame steamGame);

    List<SteamGame> toModel(List<SteamGameEntity> steamGameEntities);

    List<SteamGameEntity> toEntity(List<SteamGame> steamGames);

}
