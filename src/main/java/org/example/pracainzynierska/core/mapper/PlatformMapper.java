package org.example.pracainzynierska.core.mapper;


import org.example.pracainzynierska.core.entities.platform.PlatformEntity;
import org.example.pracainzynierska.core.web.dto.GameResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlatformMapper {

    @Mapping(target = "id", ignore = true)            // UUID primary key
    @Mapping(target = "apiId", source = "id")         // Map the API ID from DTO to entity
    PlatformEntity toEntity(GameResponse.Platform platform);

    @Mapping(target = "id", source = "apiId")         // Map the API ID from entity to DTO
    GameResponse.Platform toDto(PlatformEntity platformEntity);
}
