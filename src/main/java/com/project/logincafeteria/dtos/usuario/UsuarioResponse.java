package com.project.logincafeteria.dtos.usuario;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.project.logincafeteria.dtos.rol.RolDTO;

// Representa los datos que el backend devuelve al frontend del ADMIN 
// cuando el ADMIN solicita la lista de usuarios (GET /admin/usuario) 
// o un usuario específico (GET /admin/usuario/{id}).
// Este DTO expone únicamente los campos seguros y relevantes para la gestión administrativa.

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponse {

    private Integer idUsuario;
    private String password; // No se debe exponer en la respuesta, pero se incluye para el mapeo interno
    private String username;
    private Boolean enabled;
    private Boolean accountNonLocked;
    private Integer intentosFallidos;
    private String estado;

    // Información personal
    private String nombres;
    private String apellidos;
    private String dni;
    private LocalDate fechaNacimiento;
    private String sexo;

    // Contacto
    private String email;
    private String telefono;
    private String direccion;
    private String distrito;
    private String referencia;

    // Perfil
    private String fotoPerfil;
    private LocalDateTime fechaRegistro;
    private LocalDateTime ultimaConexion;

    // Roles

    private List<RolDTO> roles;
}
