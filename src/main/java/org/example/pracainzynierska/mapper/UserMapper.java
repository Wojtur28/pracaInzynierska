package org.example.pracainzynierska.mapper;

import com.example.model.User;
import org.example.pracainzynierska.core.entities.user.Gender;
import org.example.pracainzynierska.core.entities.user.Role;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "gender", source = "gender", qualifiedByName = "mapGenderToString")
    User toDto(UserEntity userEntity);

    @Mapping(target = "gender", source = "gender", qualifiedByName = "mapStringToGender")
    UserEntity toEntity(User user);

    List<User> toDto(List<UserEntity> userEntities);

    @Named("mapGenderToString")
    default String mapGenderToString(Gender gender) {
        return gender != null ? gender.name() : null;
    }

    @Named("mapStringToGender")
    default Gender mapStringToGender(String gender) {
        return gender != null ? Gender.valueOf(gender) : null;
    }

    @Named("mapStringToRole")
    default Role mapStringToRole(String role) {
        return role != null ? Role.valueOf(role) : null;
    }
}
