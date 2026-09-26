package com.project.logincafeteria.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// Representa los datos que el culaquier usuario (CLIENTE,ADMIN etc.) envía al backend para iniciar sesión.
// Se recibe en POST /auth/login.
// Este DTO contiene únicamente las credenciales necesarias para autenticación.
@Data
public class LoginRequest {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}