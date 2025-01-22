package org.example.pracainzynierska.core.usecase.library;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.library.LibraryEntity;
import org.example.pracainzynierska.core.entities.library.LibraryRepository;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateLibraryUseCase {

    private final LibraryRepository libraryRepository;

    public void createLibraryForUser(UserEntity user) {
        LibraryEntity library = new LibraryEntity();
        library.setUser(user);
        library.setCreatedBy(user);
        library.setModifiedBy(user);
        libraryRepository.save(library);
    }
}
