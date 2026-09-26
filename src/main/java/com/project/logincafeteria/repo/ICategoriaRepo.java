package com.project.logincafeteria.repo;

import java.util.Optional;

import com.project.logincafeteria.model.Categoria;

public interface ICategoriaRepo extends IGenericRepo<Categoria, Integer> {
    // No additional methods needed for now
    Optional<Categoria> findByNombre(String name);
}
