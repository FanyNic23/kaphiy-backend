package com.project.logincafeteria.dtos.usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Representa los datos que el ADMIN envía al backend para crear o editar un usuario.
// Se usa en POST /admin/usuario/register y PUT /admin/usuario/{id}.
// Contiene únicamente los campos que el ADMIN puede controlar directamente.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String nombres;
    @NotBlank
    private String apellidos;
    @NotBlank
    private String dni;

    private String email;
    private String telefono;
    private String direccion;
    private String distrito;
    private String referencia;
    private String estado;
    private String sexo;
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaRegistro;
    private LocalDateTime ultimaConexion;

    private String fotoPerfil;

    // can private List<String> roles; // Puede venir vacío o null

    private List<Integer> rolesIds;

    // ✅ Agregar estos campos
    private Boolean enabled = true;
    private Boolean accountNonLocked = true;
    private Integer intentosFallidos = 0;
}
