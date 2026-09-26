
package com.project.logincafeteria.exception;

import com.project.logincafeteria.exception.personalizadas.RolNoEncontradoException;
import com.project.logincafeteria.exception.personalizadas.UsuarioDuplicadoException;
import com.project.logincafeteria.dtos.global.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {

        private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

        @ExceptionHandler(UsuarioDuplicadoException.class)
        public ResponseEntity<ApiResponse> handleUsuarioDuplicado(UsuarioDuplicadoException ex) {
                logger.error("Error de usuario duplicado: {}", ex.getMessage());
                return ResponseEntity.badRequest().body(
                                ApiResponse.builder()
                                                .message(ex.getMessage())
                                                .data(null)
                                                .build());
        }

        @ExceptionHandler(RolNoEncontradoException.class)
        public ResponseEntity<ApiResponse> handleRolNoEncontrado(RolNoEncontradoException ex) {
                logger.error("Error de rol no encontrado: {}", ex.getMessage());
                return ResponseEntity.badRequest().body(
                                ApiResponse.builder()
                                                .message(ex.getMessage())
                                                .data(null)
                                                .build());
        }

        @ExceptionHandler(ModelNotFoundException.class)
        public ResponseEntity<ApiResponse> handleNotFound(ModelNotFoundException ex) {
                logger.error("Recurso no encontrado: {}", ex.getMessage());
                return ResponseEntity.status(404).body(
                                ApiResponse.builder()
                                                .message(ex.getMessage())
                                                .data(null)
                                                .build());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse> handleGeneral(Exception ex) {
                logger.error("Error general en el servidor: ", ex);
                return ResponseEntity.internalServerError().body(
                                ApiResponse.builder()
                                                .message("Error: " + ex.getMessage())
                                                .data(null)
                                                .build());
        }
}