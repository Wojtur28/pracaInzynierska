package org.example.pracainzynierska.core.usecase.library;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.library.LibraryItemRepository;
import org.example.pracainzynierska.exception.LibraryItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DeleteLibraryItemUseCase {

    private final LibraryItemRepository libraryItemRepository;

    public void deleteLibraryItem(UUID libraryItemId) {
        if (libraryItemRepository.existsById(libraryItemId)) {
            libraryItemRepository.deleteById(libraryItemId);
        } else {
            throw new LibraryItemNotFoundException("Library item with id " + libraryItemId + " does not exist");
        }
    }
}
