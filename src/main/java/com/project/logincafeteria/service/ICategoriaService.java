package com.project.logincafeteria.service;

import java.util.List;

import com.project.logincafeteria.dtos.categoria.CategoriaDTO;

import com.project.logincafeteria.model.Categoria;

public interface ICategoriaService extends IGenericService<Categoria, Integer> {
    List<CategoriaDTO> listarCategoriaDTOs();
}
