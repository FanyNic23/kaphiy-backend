package com.project.logincafeteria.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.logincafeteria.dtos.categoria.CategoriaDTO;
import com.project.logincafeteria.exception.ModelNotFoundException;
import com.project.logincafeteria.exception.personalizadas.RolVinculadoException;
import com.project.logincafeteria.model.Categoria;
import com.project.logincafeteria.repo.ICategoriaRepo;
import com.project.logincafeteria.repo.IGenericRepo;
import com.project.logincafeteria.service.ICategoriaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService extends GenericService<Categoria, Integer> implements ICategoriaService {
    private final ICategoriaRepo repo;
    private final ModelMapper modelMapper;

    @Override
    protected IGenericRepo<Categoria, Integer> getRepo() {
        return repo;
    }

    @Override
    public Categoria save(Categoria categoria) throws Exception {
        if (categoria == null || categoria.getNombre() == null || categoria.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío.");
        }

        String nombreNormalizado = categoria.getNombre().trim().toUpperCase();

        if (repo.findByNombre(nombreNormalizado).isPresent()) {
            throw new IllegalStateException("Ya existe una categoría con el nombre: " + nombreNormalizado);
        }

        categoria.setNombre(nombreNormalizado);
        return super.save(categoria);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Categoria categoria = repo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Rol no encontrado"));

        if (categoria.getProductos() != null && !categoria.getProductos().isEmpty()) {
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
    public List<CategoriaDTO> listarCategoriaDTOs() {
        return repo.findAll()
                .stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaDTO.class))
                .collect(Collectors.toList());
    }
}
