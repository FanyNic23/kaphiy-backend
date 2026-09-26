package com.project.logincafeteria.dtos.producto;

import java.math.BigDecimal;

import com.project.logincafeteria.dtos.categoria.CategoriaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precioVenta;
    private BigDecimal precioCosto;
    private BigDecimal descuento;
    private Integer stock;
    private Boolean gestionInventario;
    private Boolean disponible;
    private String imagenUrl;
    private Integer categoriaId; // este campo se mapea desde categoria.idCategoria
    private CategoriaDTO categoria;

}
