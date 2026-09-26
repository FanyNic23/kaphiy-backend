package com.project.logincafeteria.dtos.auth;

import jakarta.validation.constraints.Email;
import lombok.Data;

// Representa los datos que cualquier usuario(CLIENTE, ADMIN etc.) envía al backend para restablecer su contraseña mediante un token. 
// (Aqui el usuario aun no esta logeado)
// Se recibe en POST /auth/reset-password.
// Este DTO incluye el token de recuperación enviado por correo y la nueva contraseña deseada.
@Data
public class ResetPasswordRequest {
    @Email
    private String email;
    // Aquí agregamos un código de verificación si implementas verificación
    private String codigoVerificacion;
    private String nuevaPassword;
}
