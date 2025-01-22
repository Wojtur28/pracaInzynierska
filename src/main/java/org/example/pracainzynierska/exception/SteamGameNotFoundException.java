package org.example.pracainzynierska.exception;

import org.jetbrains.annotations.NotNull;

public class SteamGameNotFoundException extends RuntimeException {
    public SteamGameNotFoundException(@NotNull String message) {
        super(message);
    }
}
