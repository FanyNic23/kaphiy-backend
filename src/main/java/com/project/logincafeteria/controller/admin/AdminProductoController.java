package com.project.logincafeteria.controller.admin;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.logincafeteria.controller.GenericController;
import com.project.logincafeteria.dtos.global.ApiResponse;
import com.project.logincafeteria.dtos.producto.ProductoDTO;
import com.project.logincafeteria.dtos.producto.ProductoRequest;
import com.project.logincafeteria.dtos.producto.ProductoResponse;
import com.project.logincafeteria.model.Producto;
import com.project.logincafeteria.service.IProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/productos")
@CrossOrigin("*")
public class AdminProductoController extends GenericController<Producto, ProductoDTO, Integer> {

    private final IProductoService productoService;
    private final ModelMapper modelMapper;

    public AdminProductoController(IProductoService productoService, ModelMapper modelMapper) {
        super(productoService, modelMapper);
        this.productoService = productoService;
        this.modelMapper = modelMapper;
    }

    // ✅ Registrar producto
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registrar(@Valid @RequestBody ProductoRequest request) {
        Producto nuevo = productoService.registrarProductoPorAdmin(request);
        ProductoResponse dto = modelMapper.map(nuevo, ProductoResponse.class);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Producto registrado correctamente por el ADMIN")
                        .data(dto)
                        .build());
    }

    // ✅ Actualizar producto
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ProductoResponse> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ProductoRequest request) {

        // 1. Actualizar el producto en base al ID y datos recibidos
        Producto productoActualizado = productoService.editarProductoPorAdmin(id, request);

        // 2. Convertir la entidad actualizada en un DTO de respuesta
        ProductoResponse response = modelMapper.map(productoActualizado, ProductoResponse.class);

        // 3. Retornar respuesta exitosa
        return ResponseEntity.ok(response);
    }

    @Override
    protected Class<Producto> getEntityClass() {
        return Producto.class;
    }

    @Override
    protected Class<ProductoDTO> getDtoClass() {
        return ProductoDTO.class;
    }

    @Override
    protected Integer getId(Producto entity) {
        return entity.getIdProducto();
    }
}
