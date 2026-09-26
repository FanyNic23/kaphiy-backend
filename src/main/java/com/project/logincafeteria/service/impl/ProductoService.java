package com.project.logincafeteria.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.project.logincafeteria.dtos.producto.ProductoRequest;
import com.project.logincafeteria.model.Categoria;
import com.project.logincafeteria.model.Producto;
import com.project.logincafeteria.repo.ICategoriaRepo;
import com.project.logincafeteria.repo.IGenericRepo;
import com.project.logincafeteria.repo.IProductoRepo;
import com.project.logincafeteria.service.IProductoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService extends GenericService<Producto, Integer> implements IProductoService {

    private final IProductoRepo repo;
    private final ICategoriaRepo categoriaRepo;
    private final ModelMapper modelMapper;

    @Override
    protected IGenericRepo<Producto, Integer> getRepo() {
        return repo;
    }

    @Override
    public Producto registrarProductoPorAdmin(ProductoRequest request) {
        if (request.getNombre() == null || request.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        if (request.getCategoriaId() == null) {
            throw new IllegalArgumentException("Debe seleccionar una categoría");
        }

        // Buscar la categoría
        Categoria categoria = categoriaRepo.findById(request.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no válida: ID " + request.getCategoriaId()));

        // Mapear DTO a entidad
        Producto producto = modelMapper.map(request, Producto.class);
        producto.setCategoria(categoria);

        // Guardar y retornar
        return repo.save(producto);
    }

    @Override
    public Producto editarProductoPorAdmin(Integer id, ProductoRequest request) {
        // 1. Buscar producto existente
        Producto producto = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: ID " + id));

        // 2. Validar nombre duplicado
        if (!producto.getNombre().equalsIgnoreCase(request.getNombre()) &&
                repo.existsByNombre(request.getNombre())) {
            throw new RuntimeException("El nombre del producto ya existe");
        }

        // 3. Mapear campos simples (no relaciones)
        modelMapper.map(request, producto);

        // 4. Obtener la nueva categoría desde el ID
        if (request.getCategoriaId() != null) {
            Categoria categoria = categoriaRepo.findById(request.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no válida: ID " + request.getCategoriaId()));
            producto.setCategoria(categoria);
        } else {
            throw new IllegalArgumentException("El producto debe tener una categoría asignada");
        }

        // 5. Guardar y retornar
        return repo.save(producto);
    }

}
