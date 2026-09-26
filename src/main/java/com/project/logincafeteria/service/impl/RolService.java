package com.project.logincafeteria.service.impl;

import com.project.logincafeteria.dtos.rol.RolDTO;
import com.project.logincafeteria.exception.ModelNotFoundException;
import com.project.logincafeteria.exception.personalizadas.RolVinculadoException;
import com.project.logincafeteria.model.Rol;
import com.project.logincafeteria.repo.IGenericRepo;
import com.project.logincafeteria.repo.IRolRepo;
import com.project.logincafeteria.service.IRolService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para la gestión de roles en el sistema.
 * Incluye operaciones CRUD y validaciones adicionales como:
 * - Normalización del nombre del rol (prefijo ROLE_)
 * - Prevención de eliminación si el rol está vinculado a usuarios
 * - Listado de roles como DTO para el frontend
 */
@Service
@RequiredArgsConstructor
public class RolService extends GenericService<Rol, Integer> implements IRolService {

    private final IRolRepo repo;
    private final ModelMapper modelMapper;

    @Override
    protected IGenericRepo<Rol, Integer> getRepo() {
        return repo;
    }

    @Override
    public Rol save(Rol rol) throws Exception {
        String nombreRol = rol.getName().toUpperCase();
        if (!nombreRol.startsWith("ROLE_")) {
            nombreRol = "ROLE_" + nombreRol;
        }

        if (repo.findByName(nombreRol).isPresent()) {
            throw new Exception("El rol ya existe: " + nombreRol);
        }

        rol.setName(nombreRol);
        return super.save(rol);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Rol rol = repo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Rol no encontrado"));

        if (rol.getUsuarios() != null && !rol.getUsuarios().isEmpty()) {
            throw new RolVinculadoException("No se puede eliminar el rol porque está vinculado a usuarios.");
        }

        super.delete(id);
    }

    /**
     * Lista todos los roles en forma de DTO para el frontend.
     *
     * 
     */
    @Override
    public List<RolDTO> listarRolesDTOs() {
        return repo.findAll()
                .stream()
                .map(rol -> modelMapper.map(rol, RolDTO.class))
                .collect(Collectors.toList());
    }

}
