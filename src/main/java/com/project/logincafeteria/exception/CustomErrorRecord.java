package com.project.logincafeteria.exception;

import java.time.LocalDateTime;

//Para errores simples(como ModelNotFoundException,Exception,etc.)

public record CustomErrorRecord(
                LocalDateTime datetime,
                String message,
                String details) {
}
