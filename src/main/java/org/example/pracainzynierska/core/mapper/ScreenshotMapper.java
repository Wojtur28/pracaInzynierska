package org.example.pracainzynierska.core.mapper;

import org.example.pracainzynierska.core.entities.screenshot.ScreenshotEntity;
import org.example.pracainzynierska.core.web.dto.GameResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScreenshotMapper {

    @Mapping(target = "id", ignore = true)            // UUID primary key
    @Mapping(target = "apiId", source = "id")         // Map the API ID from DTO to entity
    @Mapping(target = "imageId", source = "image_id")
    ScreenshotEntity toEntity(GameResponse.Screenshot screenshot);

    @Mapping(target = "id", source = "apiId")         // Map the API ID from entity to DTO
    @Mapping(target = "image_id", source = "imageId")
    GameResponse.Screenshot toDto(ScreenshotEntity screenshotEntity);
}
