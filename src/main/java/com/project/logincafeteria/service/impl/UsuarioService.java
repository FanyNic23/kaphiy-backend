package com.project.logincafeteria.service.impl;

import com.project.logincafeteria.dtos.auth.RegisterRequest;
import com.project.logincafeteria.dtos.usuario.UsuarioRequest;
import com.project.logincafeteria.exception.personalizadas.UsuarioDuplicadoException;
import com.project.logincafeteria.model.Rol;
import com.project.logincafeteria.model.Usuario;
import com.project.logincafeteria.repo.IGenericRepo;
import com.project.logincafeteria.repo.IRolRepo;
import com.project.logincafeteria.repo.IUsuarioRepo;
import com.project.logincafeteria.service.IUsuarioService;
import java.util.stream.Collectors;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService extends GenericService<Usuario, Integer> implements IUsuarioService {

    private final IUsuarioRepo repo;
    private final IRolRepo rolRepo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    protected IGenericRepo<Usuario, Integer> getRepo() {
        return repo;
    }

    @Override
    public boolean existsByUsername(String username) {
        return repo.existsByUsername(username);
    }

    @Override
    public Usuario registrarUsuarioPublico(RegisterRequest request) {

        if (repo.existsByUsername(request.getUsername())) {
            throw new UsuarioDuplicadoException("El nombre de usuario ya existe");
        }
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        // DTO ➡ Entidad
        // Para convertir los datos de registro a un Usuario completo
        Usuario usuario = modelMapper.map(request, Usuario.class);
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        Rol rolCliente = rolRepo.findByName("ROLE_CLIENTE")
                .orElseThrow(() -> new RuntimeException("Rol no válido: ROLE_CLIENTE"));

        // ✅ Asignar el rol en una lista inmutable de un solo elemento
        usuario.setRoles(Collections.singletonList(rolCliente));

        // Opcional: establecer valores por defecto
        usuario.setEnabled(true);
        usuario.setAccountNonLocked(true);
        usuario.setIntentosFallidos(0);
        usuario.setEstado("ACTIVO");

        return repo.save(usuario);
    }

    // @Transactional
    @Override
    public Usuario registrarUsuarioPorAdmin(UsuarioRequest request) {

        if (repo.existsByUsername(request.getUsername())) {
            throw new UsuarioDuplicadoException("El nombre de usuario ya existe");
        }
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (request.getRolesIds() == null || request.getRolesIds().isEmpty()) {
            throw new IllegalArgumentException("El usuario debe tener al menos un rol");
        }

        // DTO ➡ Entidad
        // Para convertir los datos de registro a un Usuario completo pero con más
        // lógica de roles
        Usuario usuario = modelMapper.map(request, Usuario.class);
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        List<Rol> roles = request.getRolesIds().stream()
                .map(id -> rolRepo.findById(id)
                        .orElseThrow(() -> new RuntimeException("Rol no válido: ID " + id)))
                .collect(Collectors.toList());

        usuario.setRoles(roles);
        return repo.save(usuario);
    }

    @Override
    public Usuario editarUsuarioPorAdmin(Integer id, UsuarioRequest request) {
        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getUsername().equals(request.getUsername()) &&
                repo.existsByUsername(request.getUsername())) {
            throw new UsuarioDuplicadoException("El nombre de usuario ya existe");
        }
        if (request.getPassword() != null && request.getPassword().isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (request.getRolesIds() != null && request.getRolesIds().isEmpty()) {
            throw new IllegalArgumentException("El usuario debe tener al menos un rol");
        }

        // DTO ➡ Entidad
        // Para actualizar un usuario existente con nuevos datos
        modelMapper.map(request, usuario);

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getRolesIds() != null && !request.getRolesIds().isEmpty()) {
            List<Rol> roles = request.getRolesIds().stream()
                    .map(rolId -> rolRepo.findById(rolId)
                            .orElseThrow(() -> new RuntimeException("Rol no válido: ID " + rolId)))
                    .collect(Collectors.toList());
            usuario.setRoles(roles);
        }

        return repo.save(usuario);
    }

}
