package com.project.logincafeteria.repo;

import com.project.logincafeteria.model.Producto;
import com.project.logincafeteria.model.Usuario;

public interface IProductoRepo extends IGenericRepo<Producto, Integer> {
    boolean existsByNombre(String nombre); // ✅ Usado en registro (para validar duplicado)
}
