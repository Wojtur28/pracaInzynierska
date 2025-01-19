package org.example.pracainzynierska.exception;

import org.jetbrains.annotations.NotNull;

public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(@NotNull String message) {
        super(message);
    }
}
