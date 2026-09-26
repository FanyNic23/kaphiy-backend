
package com.project.logincafeteria.service;

import com.project.logincafeteria.dtos.auth.RegisterRequest;
import com.project.logincafeteria.dtos.usuario.UsuarioRequest;
import com.project.logincafeteria.model.Usuario;

public interface IUsuarioService extends IGenericService<Usuario, Integer> {

    // Verifica si ya existe un username
    boolean existsByUsername(String username);

    // Registro público (CLIENTE) — solo recibe RegisterRequest
    Usuario registrarUsuarioPublico(RegisterRequest request);

    // Registro/edición por ADMIN — recibe UsuarioRequest (con roles, estado, etc.)
    Usuario registrarUsuarioPorAdmin(UsuarioRequest request);

    Usuario editarUsuarioPorAdmin(Integer id, UsuarioRequest request);
}
