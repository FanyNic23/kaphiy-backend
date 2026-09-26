package com.project.logincafeteria.controller.admin;

import com.project.logincafeteria.controller.GenericController;
import com.project.logincafeteria.dtos.global.ApiResponse;
import com.project.logincafeteria.dtos.usuario.UsuarioDTO;
import com.project.logincafeteria.dtos.usuario.UsuarioRequest;
import com.project.logincafeteria.dtos.usuario.UsuarioResponse;
import com.project.logincafeteria.model.Usuario;
import com.project.logincafeteria.service.IUsuarioService;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/usuarios")
@CrossOrigin("*")
public class AdminUsuarioController extends GenericController<Usuario, UsuarioDTO, Integer> {

    private final IUsuarioService usuarioService;
    private final ModelMapper modelMapper;

    public AdminUsuarioController(IUsuarioService usuarioService, ModelMapper modelMapper) {
        super(usuarioService, modelMapper); // ✅ Llama al constructor del padre
        this.usuarioService = usuarioService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registrar(@Valid @RequestBody UsuarioRequest request) {
        // Registrar usuario (Admin, cliente etc)
        Usuario nuevo = usuarioService.registrarUsuarioPorAdmin(request);
        // Mapear la respuesta Entidad ➡ DTO
        UsuarioResponse dto = modelMapper.map(nuevo, UsuarioResponse.class);
        // Retornar respuesta con DTO
        // ✅ Utiliza ApiResponse para una respuesta más estructurada
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Usuario registrado correctamente por el ADMIN")
                        .data(dto)
                        .build());
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<UsuarioResponse> actualizar(@PathVariable Integer id,
            @Valid @RequestBody UsuarioRequest request) {
        // Mapear la respuesta Entidad ➡ DTO
        Usuario actualizado = usuarioService.editarUsuarioPorAdmin(id, request);
        // Verifica si el usuario fue encontrado
        return ResponseEntity.ok(modelMapper.map(actualizado, UsuarioResponse.class));
    }

    @Override
    protected Class<Usuario> getEntityClass() {
        return Usuario.class;
    }

    @Override
    protected Class<UsuarioDTO> getDtoClass() {
        return UsuarioDTO.class;
    }

    @Override
    protected Integer getId(Usuario entity) {
        return entity.getIdUsuario();
    }

}
