package org.example.pracainzynierska.mapper;

import com.example.model.Report;
import org.example.pracainzynierska.core.entities.raport.ReportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {BaseMapper.class, GameRatingMapper.class, UserMapper.class})
public interface ReportMapper {

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "offsetDateTimeToLocalDateTime")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "offsetDateTimeToLocalDateTime")
    ReportEntity toEntity(Report report);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localDateTimeToOffsetDateTime")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "localDateTimeToOffsetDateTime")
    Report toDto(ReportEntity reportEntity);

    List<Report> toDto(List<ReportEntity> reportEntities);

    List<ReportEntity> toEntity(List<Report> reports);
}
