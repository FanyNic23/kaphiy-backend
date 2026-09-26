package com.project.logincafeteria.exception;

//Este es tu handler global de errores, ahora profesionalmente estructurado:

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.logincafeteria.exception.personalizadas.RolVinculadoException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

        // Excepción genérica
        @ExceptionHandler(Exception.class)
        public ResponseEntity<CustomErrorRecord> handleAllExceptions(Exception ex, WebRequest request) {
                CustomErrorRecord error = new CustomErrorRecord(
                                LocalDateTime.now(),
                                "Error interno del servidor: " + ex.getMessage(),
                                request.getDescription(false));
                return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Excepción personalizada para recursos no encontrados
        @ExceptionHandler(ModelNotFoundException.class)
        public ResponseEntity<CustomErrorRecord> handleModelNotFound(ModelNotFoundException ex, WebRequest request) {
                CustomErrorRecord error = new CustomErrorRecord(
                                LocalDateTime.now(),
                                ex.getMessage(),
                                request.getDescription(false));
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        // Excepción aritmética
        @ExceptionHandler(ArithmeticException.class)
        public ResponseEntity<CustomErrorRecord> handleArithmetic(ArithmeticException ex, WebRequest request) {
                CustomErrorRecord error = new CustomErrorRecord(
                                LocalDateTime.now(),
                                "Error aritmético: " + ex.getMessage(),
                                request.getDescription(false));
                return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
        }

        // Validaciones con @Valid (campos no válidos)
        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(
                        MethodArgumentNotValidException ex, HttpHeaders headers,
                        HttpStatusCode status, WebRequest request) {

                List<ValidationError> validationErrors = ex.getBindingResult().getFieldErrors().stream()
                                .map(fieldError -> new ValidationError(
                                                fieldError.getField(),
                                                fieldError.getDefaultMessage()))
                                .collect(Collectors.toList());

                CustomErrorResponse errorResponse = new CustomErrorResponse(
                                LocalDateTime.now(),
                                "Error de validación",
                                request.getDescription(false),
                                validationErrors);

                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        // eror al elimnar un rol vinculado a un usuario
        @ExceptionHandler(RolVinculadoException.class)
        public ResponseEntity<CustomErrorRecord> handleRolVinculado(RolVinculadoException ex, WebRequest request) {
                CustomErrorRecord err = new CustomErrorRecord(
                                LocalDateTime.now(),
                                ex.getMessage(),
                                request.getDescription(false));
                return new ResponseEntity<>(err, HttpStatus.CONFLICT); // 409
        }
}
