package com.project.logincafeteria.controller.admin;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.logincafeteria.controller.GenericController;
import com.project.logincafeteria.dtos.categoria.CategoriaDTO;
import com.project.logincafeteria.model.Categoria;
import com.project.logincafeteria.service.ICategoriaService;

@RestController
@RequestMapping("/admin/categorias")
@CrossOrigin("*")
public class AdminCategoriaController extends GenericController<Categoria, CategoriaDTO, Integer> {

    public AdminCategoriaController(ICategoriaService service, ModelMapper modelMapper) {
        super(service, modelMapper);
    }

    @Override
    protected Class<Categoria> getEntityClass() {
        return Categoria.class;
    }

    @Override
    protected Class<CategoriaDTO> getDtoClass() {
        return CategoriaDTO.class;
    }

    @Override
    protected Integer getId(Categoria entity) {
        return entity.getIdCategoria();
    }
}
