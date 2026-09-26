package com.project.logincafeteria.dtos.usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.project.logincafeteria.dtos.rol.RolDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Integer idUsuario;

    private String username;
    private String password;

    private Boolean enabled;
    private Boolean accountNonLocked;
    private String estado;

    private String nombres;
    private String apellidos;
    private String dni;

    private LocalDate fechaNacimiento;
    private String sexo;

    private String email;
    private String telefono;
    private String direccion;
    private String distrito;
    private String referencia;

    private String fotoPerfil;

    private LocalDateTime fechaRegistro;
    private LocalDateTime ultimaConexion;

    private List<RolDTO> roles;
}