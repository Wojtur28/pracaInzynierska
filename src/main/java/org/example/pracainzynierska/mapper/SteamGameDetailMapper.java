package org.example.pracainzynierska.mapper;

import com.example.model.SteamGameDetail;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameDetailEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BaseMapper.class})
public interface SteamGameDetailMapper {

    SteamGameDetail toDto(SteamGameDetailEntity steamGameDetailEntity);

    SteamGameDetailEntity toEntity(SteamGameDetail steamGameDetail);

    List<SteamGameDetail> toDto(List<SteamGameDetailEntity> steamGameDetailEntities);

    List<SteamGameDetailEntity> toEntity(List<SteamGameDetail> steamGameDetails);
}
