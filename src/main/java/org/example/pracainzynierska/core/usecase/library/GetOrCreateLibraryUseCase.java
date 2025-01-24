package org.example.pracainzynierska.core.usecase.library;

import com.example.model.Library;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.library.LibraryEntity;
import org.example.pracainzynierska.core.entities.library.LibraryRepository;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.example.pracainzynierska.mapper.LibraryMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetOrCreateLibraryUseCase {
    private final LibraryRepository libraryRepository;
    private final UserRepository userRepository;
    private final LibraryMapper libraryMapper;

    public Library getOrCreateLibraryForCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        LibraryEntity library = libraryRepository.findByUser_Id(user.getId())
                .orElseGet(() -> {
                    LibraryEntity newLibrary = new LibraryEntity();
                    newLibrary.setUser(user);
                    return libraryRepository.save(newLibrary);
                });
        return libraryMapper.toDto(library);
    }
}
