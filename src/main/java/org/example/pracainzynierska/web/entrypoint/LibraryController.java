package org.example.pracainzynierska.web.entrypoint;

import com.example.api.LibraryApi;
import com.example.model.*;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.usecase.library.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class LibraryController implements LibraryApi {

    private final CreateLibraryItemUseCase createLibraryItemUseCase;
    private final UpdateLibraryItemStatusUseCase updateLibraryItemStatusUseCase;
    private final GetOrCreateLibraryUseCase getOrCreateLibraryUseCase;

    @Override
    public ResponseEntity<Library> getOrCreateLibraryForCurrentUser() {
        return ResponseEntity.ok(getOrCreateLibraryUseCase.getOrCreateLibraryForCurrentUser());
    }

    @Override
    public ResponseEntity<LibraryItem> addLibraryItem(@RequestBody CreateLibraryItem createLibraryItem) {
        return ResponseEntity.ok(createLibraryItemUseCase.addGameToLibrary(createLibraryItem));
    }

    @Override
    public ResponseEntity<LibraryItem> updateLibraryItemStatus(@PathVariable("steamGameId") UUID steamGameId, UpdateLibraryItem body) {
        return ResponseEntity.ok(updateLibraryItemStatusUseCase.updateLibraryItemStatus(steamGameId, body));
    }
}
