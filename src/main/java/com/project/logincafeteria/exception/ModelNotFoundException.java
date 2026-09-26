package com.project.logincafeteria.exception;

//Tu excepción personalizada para recursos no encontrados

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String message) {
        super(message);
    }
}
