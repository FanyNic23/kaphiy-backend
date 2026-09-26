package com.project.logincafeteria.repo;

import java.util.Optional;

import com.project.logincafeteria.model.Rol;

public interface IRolRepo extends IGenericRepo<Rol, Integer> {
    Optional<Rol> findByName(String name);
}