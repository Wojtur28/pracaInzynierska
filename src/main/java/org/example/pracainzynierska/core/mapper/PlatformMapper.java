package org.example.pracainzynierska.core.mapper;


import org.example.pracainzynierska.core.entities.platform.PlatformEntity;
import org.example.pracainzynierska.core.web.dto.GameResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlatformMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apiId", source = "id")
    PlatformEntity toEntity(GameResponse.Platform platform);

    @Mapping(target = "id", source = "apiId")
    GameResponse.Platform toDto(PlatformEntity platformEntity);
}
