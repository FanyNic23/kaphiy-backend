package com.project.logincafeteria.repo;

import com.project.logincafeteria.model.Usuario;

public interface IUsuarioRepo extends IGenericRepo<Usuario, Integer> {

    Usuario findOneByUsername(String username); // ✅ Usado en login

    boolean existsByUsername(String username); // ✅ Usado en registro (para validar duplicado)

}
