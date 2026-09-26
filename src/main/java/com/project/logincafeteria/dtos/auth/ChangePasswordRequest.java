package com.project.logincafeteria.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// Representa los datos que el cliente autenticado envía al backend para cambiar su propia contraseña.
// (Aqui el usuario ya esta logeado)
// Se recibe en PUT /auth/change-password.
// Este DTO obliga al usuario a confirmar su contraseña actual antes de asignar la nueva.
@Data
public class ChangePasswordRequest {
    @NotBlank
    private String actualPassword;

    @NotBlank
    private String nuevaPassword;
}