package com.project.logincafeteria.dtos.producto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequest {

    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precioVenta;
    private BigDecimal precioCosto;
    private Integer descuento;
    private Integer stock;
    private Boolean gestionInventario;
    private Boolean disponible;
    private String imagenUrl;
    private Integer categoriaId; // ✅ Solo se envía esto

}
