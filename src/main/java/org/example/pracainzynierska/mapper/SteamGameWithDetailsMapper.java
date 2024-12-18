package org.example.pracainzynierska.mapper;

import com.example.model.SteamGameWithDetails;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {SteamGameMapper.class, SteamGameDetailMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SteamGameWithDetailsMapper {

    @Mapping(source = "steamGameDetailEntity.headerImage", target = "headerImage")
    @Mapping(source = "steamGameDetailEntity.capsuleImage", target = "capsuleImage")
    @Mapping(source = "steamGameDetailEntity.developer", target = "developer")
    @Mapping(source = "steamGameDetailEntity.publisher", target = "publisher")
    @Mapping(source = "steamGameDetailEntity.requiredAge", target = "requiredAge")
    @Mapping(source = "steamGameDetailEntity.supportedLanguages", target = "supportedLanguages")
    @Mapping(source = "steamGameDetailEntity.categories", target = "categories")
    @Mapping(source = "steamGameDetailEntity.genres", target = "genres")
    @Mapping(source = "steamGameDetailEntity.platforms", target = "platforms")
    SteamGameWithDetails toDto(SteamGameEntity steamGameEntity);
}
