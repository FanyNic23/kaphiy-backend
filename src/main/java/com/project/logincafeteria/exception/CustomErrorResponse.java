package com.project.logincafeteria.exception;

// Para devolver una lista de errores de validación

import java.time.LocalDateTime;
import java.util.List;

public record CustomErrorResponse(
                LocalDateTime timestamp,
                String message,
                String details,
                List<ValidationError> errors) {
}
