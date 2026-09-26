package com.project.logincafeteria.controller.usuario;

import com.project.logincafeteria.controller.GenericController;
import com.project.logincafeteria.dtos.auth.RegisterRequest;
import com.project.logincafeteria.dtos.global.ApiResponse;
import com.project.logincafeteria.dtos.usuario.UsuarioDTO;
import com.project.logincafeteria.dtos.usuario.UsuarioResponse;
import com.project.logincafeteria.model.Usuario;
import com.project.logincafeteria.service.IUsuarioService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController extends GenericController<Usuario, UsuarioDTO, Integer> {

    private final IUsuarioService usuarioService;
    private final ModelMapper modelMapper;

    public UsuarioController(IUsuarioService usuarioService, ModelMapper modelMapper) {
        super(usuarioService, modelMapper);
        this.usuarioService = usuarioService;
        this.modelMapper = modelMapper;
    }

    /**
     * Endpoint público para registrar usuarios tipo CLIENTE.
     * POST /usuarios/register
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registrarCliente(@Valid @RequestBody RegisterRequest request) {
        Usuario usuario = usuarioService.registrarUsuarioPublico(request);
        UsuarioResponse dto = modelMapper.map(usuario, UsuarioResponse.class);

        return ResponseEntity.ok(ApiResponse.builder()
                .message("Usuario registrado correctamente como CLIENTE")
                .data(dto)
                .build());
    }

    // Métodos requeridos por GenericController
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
