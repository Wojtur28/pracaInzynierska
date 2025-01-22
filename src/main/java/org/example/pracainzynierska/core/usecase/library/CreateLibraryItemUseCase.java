package org.example.pracainzynierska.core.usecase.library;

import com.example.model.CreateLibraryItem;
import com.example.model.LibraryItem;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.library.*;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameRepository;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.example.pracainzynierska.exception.LibraryNotFoundException;
import org.example.pracainzynierska.exception.SteamGameNotFoundException;
import org.example.pracainzynierska.exception.UserNotFoundException;
import org.example.pracainzynierska.mapper.LibraryItemMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateLibraryItemUseCase {

    private final LibraryRepository libraryRepository;
    private final LibraryItemRepository libraryItemRepository;
    private final SteamGameRepository steamGameRepository;
    private final UserRepository userRepository;
    private final LibraryItemMapper libraryItemMapper;

    public LibraryItem addGameToLibrary(CreateLibraryItem createLibraryItem) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        LibraryEntity library = libraryRepository.findByUser_Id(user.getId())
                .orElseThrow(() -> new LibraryNotFoundException("Library not found"));
        SteamGameEntity game = steamGameRepository.findById(createLibraryItem.getSteamGameId())
                .orElseThrow(() -> new SteamGameNotFoundException("Game not found"));
        Optional<LibraryItemEntity> existing = library.getLibraryItems().stream()
                .filter(i -> i.getSteamGameEntity().getId().equals(createLibraryItem.getSteamGameId()))
                .findFirst();

        if(existing.isPresent()){
            return libraryItemMapper.toDto(existing.get());
        }

        LibraryItemEntity newItem = new LibraryItemEntity();
        newItem.setSteamGameEntity(game);
        newItem.setLibrary(library);
        newItem.setGameStatus(GameStatus.NOT_STARTED);

        library.getLibraryItems().add(newItem);
        LibraryItemEntity savedItem = libraryItemRepository.save(newItem);
        return libraryItemMapper.toDto(savedItem);
    }
}
