package org.example.pracainzynierska.exception;

import org.jetbrains.annotations.NotNull;

public class LibraryItemNotFoundException extends RuntimeException {
    public LibraryItemNotFoundException(@NotNull String message) {
        super(message);
    }
}
