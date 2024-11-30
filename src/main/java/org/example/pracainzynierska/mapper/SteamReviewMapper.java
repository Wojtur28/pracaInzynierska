package org.example.pracainzynierska.mapper;

import org.example.pracainzynierska.core.entities.steam.review.SteamReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.model.SteamReview;

import java.util.List;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface SteamReviewMapper {

    @Mapping(source = "timestampCreated", target = "timestampCreated", qualifiedByName = "localDateTimeToOffsetDateTime")
    SteamReview toModel(SteamReviewEntity steamReviewEntity);

    @Mapping(source = "timestampCreated", target = "timestampCreated", qualifiedByName = "offsetDateTimeToLocalDateTime")
    @Mapping(target = "game", ignore = true)
    SteamReviewEntity toEntity(SteamReview steamReview);

    List<SteamReview> toModel(List<SteamReviewEntity> steamReviewEntities);

    List<SteamReviewEntity> toEntity(List<SteamReview> steamReviews);
}

