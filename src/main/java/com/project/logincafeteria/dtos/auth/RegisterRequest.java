package com.project.logincafeteria.dtos.auth;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Representa los datos que usuario (CLIENTE) envía al backend para registrarse como usuario público.
// Se recibe en POST /auth/register.
// Este DTO solo incluye los campos mínimos para crear una cuenta de cliente.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @Email
    private String email;

    private String telefono;
    private String direccion;
    private String distrito;
    private String referencia;

    private LocalDate fechaNacimiento;
    private String sexo;

    // ✅ NUEVO: lista de roles que el usuario tendrá (por ejemplo: ["CLIENTE"])
    private List<String> roles; // Puede venir vacío o null

}