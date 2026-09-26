package com.project.logincafeteria.exception;

// Para validar campos específicos(útil con @Valid)

public record ValidationError(
        String field,
        String message) {
}
