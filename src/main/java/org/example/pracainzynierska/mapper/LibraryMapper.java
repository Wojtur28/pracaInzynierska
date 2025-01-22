package org.example.pracainzynierska.mapper;

import com.example.model.Library;
import org.example.pracainzynierska.core.entities.library.LibraryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {LibraryItemMapper.class, UserMapper.class})
public interface LibraryMapper {

    @Mapping(target = "userId", source = "user.id")
    Library toDto(LibraryEntity libraryEntity);

    LibraryEntity toEntity(Library library);
}
