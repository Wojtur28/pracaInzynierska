package org.example.pracainzynierska.mapper;

import com.example.model.LibraryItem;
import org.example.pracainzynierska.core.entities.library.LibraryItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SteamGameMapper.class})
public interface LibraryItemMapper {

    @Mapping(target = "steamGame", source = "steamGameEntity")
    LibraryItem toDto(LibraryItemEntity libraryItemEntity);

    LibraryItemEntity toEntity(LibraryItem libraryItem);
}
