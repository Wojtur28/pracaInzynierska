package org.example.pracainzynierska.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BaseMapper {

    @Named("localDateTimeToOffsetDateTime")
    static OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.atOffset(ZoneOffset.UTC) : null;
    }

    @Named("offsetDateTimeToLocalDateTime")
    static LocalDateTime offsetDateTimeToLocalDateTime(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null ? offsetDateTime.toLocalDateTime() : null;
    }

    default UUID map(java.lang.String value){
        if(value == null || value.isEmpty()){
            return null;
        }
        return UUID.fromString(value);
    }

    default String map(UUID uuid){
        if(uuid!=null) {
            return uuid.toString();
        }
        return null;
    }
}
