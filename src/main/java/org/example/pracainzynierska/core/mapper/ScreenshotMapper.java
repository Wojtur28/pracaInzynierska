package org.example.pracainzynierska.core.mapper;

import org.example.pracainzynierska.core.entities.screenshot.ScreenshotEntity;
import org.example.pracainzynierska.core.web.dto.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScreenshotMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apiId", source = "id")
    @Mapping(target = "imageId", source = "image_id")
    ScreenshotEntity toEntity(Game.Screenshot screenshot);

    @Mapping(target = "id", source = "apiId")
    @Mapping(target = "image_id", source = "imageId")
    Game.Screenshot toDto(ScreenshotEntity screenshotEntity);
}
