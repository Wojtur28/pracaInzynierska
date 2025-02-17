package org.example.pracainzynierska.core.usecase.library;

import com.example.model.LibraryItem;
import com.example.model.UpdateLibraryItem;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.library.*;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.example.pracainzynierska.exception.LibraryItemNotFoundException;
import org.example.pracainzynierska.exception.LibraryNotFoundException;
import org.example.pracainzynierska.mapper.LibraryItemMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdateLibraryItemStatusUseCase {

    private final LibraryRepository libraryRepository;
    private final LibraryItemRepository libraryItemRepository;
    private final UserRepository userRepository;
    private final LibraryItemMapper libraryItemMapper;

    public LibraryItem updateLibraryItemStatus(UUID libraryItemId, UpdateLibraryItem updateLibraryItem) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user =
                userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));

        libraryRepository.findByUser_Id(user.getId())
                .orElseThrow(() -> new LibraryNotFoundException("Library not found"));

        LibraryItemEntity item = libraryItemRepository.findById(libraryItemId)
                .orElseThrow(() -> new LibraryItemNotFoundException("Library item not found"));

        item.setGameStatus(GameStatus.valueOf(updateLibraryItem.getGameStatus().toString()));
        LibraryItemEntity updatedItem = libraryItemRepository.save(item);
        return libraryItemMapper.toDto(updatedItem);
    }
}
