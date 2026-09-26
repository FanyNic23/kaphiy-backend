package com.project.logincafeteria.service;

import java.util.List;

import com.project.logincafeteria.dtos.rol.RolDTO;
import com.project.logincafeteria.model.Rol;

public interface IRolService extends IGenericService<Rol, Integer> {

    List<RolDTO> listarRolesDTOs();
}
