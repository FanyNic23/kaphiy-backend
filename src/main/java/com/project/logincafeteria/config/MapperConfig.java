
package com.project.logincafeteria.config;

import com.project.logincafeteria.dtos.producto.ProductoDTO;
import com.project.logincafeteria.dtos.producto.ProductoRequest;
import com.project.logincafeteria.dtos.producto.ProductoResponse;
import com.project.logincafeteria.dtos.rol.RolDTO;
import com.project.logincafeteria.dtos.usuario.UsuarioDTO;
import com.project.logincafeteria.dtos.usuario.UsuarioResponse;
import com.project.logincafeteria.model.Producto;
import com.project.logincafeteria.model.Usuario;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class MapperConfig {

    @Bean(name = "defaultMapper")
    public ModelMapper modelMapper() {

        ModelMapper mapper = new ModelMapper();

        // Mapeo de Usuario -> UsuarioDTO con roles como RolDTO
        mapper.addMappings(new PropertyMap<Usuario, UsuarioDTO>() {
            @Override
            protected void configure() {
                using(ctx -> {
                    Usuario usuario = (Usuario) ctx.getSource();
                    if (usuario.getRoles() == null) {
                        return Collections.emptyList();
                    }
                    return usuario.getRoles().stream()
                            .map(rol -> new RolDTO(rol.getIdRol(), rol.getName(), rol.getDescription()))
                            .toList();
                }).map(source, destination.getRoles());
            }
        });

        // Mapeo de Usuario -> UsuarioResponse con roles como RolDTO también (si lo
        // usas
        // así)
        mapper.addMappings(new PropertyMap<Usuario, UsuarioResponse>() {
            @Override
            protected void configure() {
                using(ctx -> {
                    Usuario usuario = (Usuario) ctx.getSource();
                    if (usuario.getRoles() == null) {
                        return Collections.emptyList();
                    }
                    return usuario.getRoles().stream()
                            .map(rol -> new RolDTO(rol.getIdRol(), rol.getName(), rol.getDescription()))
                            .toList();
                }).map(source, destination.getRoles());
            }
        });

        return mapper;
    }

}