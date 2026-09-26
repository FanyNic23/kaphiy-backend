package com.project.logincafeteria.service;

import com.project.logincafeteria.dtos.producto.ProductoRequest;
import com.project.logincafeteria.model.Producto;

public interface IProductoService extends IGenericService<Producto, Integer> {

    Producto registrarProductoPorAdmin(ProductoRequest request);

    Producto editarProductoPorAdmin(Integer id, ProductoRequest request);
}
