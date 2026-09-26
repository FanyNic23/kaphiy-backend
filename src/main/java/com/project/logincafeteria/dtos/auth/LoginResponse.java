package com.project.logincafeteria.dtos.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

// Representa los datos que el backend devuelve a cualquier usuario tras un login exitoso.
// Se retorna en POST /auth/login.
// Este DTO expone el token JWT, su tiempo de expiración y los roles del usuario.

// Enviamos también username y roles
public record LoginResponse(
                @JsonProperty("access_token") String accessToken,
                @JsonProperty("username") String username,
                @JsonProperty("roles") List<String> roles) {
}
