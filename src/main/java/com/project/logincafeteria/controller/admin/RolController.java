package com.project.logincafeteria.controller.admin;

import com.project.logincafeteria.controller.GenericController;
import com.project.logincafeteria.dtos.rol.RolDTO;
import com.project.logincafeteria.model.Rol;
import com.project.logincafeteria.service.IRolService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/roles")
@CrossOrigin("*")
public class RolController extends GenericController<Rol, RolDTO, Integer> {

    public RolController(IRolService service, ModelMapper modelMapper) {
        super(service, modelMapper);
    }

    @Override
    protected Class<Rol> getEntityClass() {
        return Rol.class;
    }

    @Override
    protected Class<RolDTO> getDtoClass() {
        return RolDTO.class;
    }

    @Override
    protected Integer getId(Rol entity) {
        return entity.getIdRol();
    }
}
