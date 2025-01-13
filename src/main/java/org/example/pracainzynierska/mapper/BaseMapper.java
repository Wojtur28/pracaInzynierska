package org.example.pracainzynierska.mapper;

import org.example.pracainzynierska.core.entities.user.UserEntity;
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

    default UUID stringToUuid(java.lang.String value){
        if(value == null || value.isEmpty()){
            return null;
        }
        return UUID.fromString(value);
    }

    default String uuidToString(UUID uuid){
        if(uuid!=null) {
            return uuid.toString();
        }
        return null;
    }

    @Named("userEntityToUuid")
    default UUID userEntityToUuid(UserEntity userEntity) {
        return userEntity != null ? userEntity.getId() : null;
    }

    @Named("uuidToUserEntity")
    default UserEntity uuidToUserEntity(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(uuid);
        return userEntity;
    }
}
