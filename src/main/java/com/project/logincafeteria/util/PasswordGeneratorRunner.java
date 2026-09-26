///ESto de aqui no tiene nada que ver con mi program espara generar contraseñas cuando se ejecuta el programa  por ejemploperico y me da un acontraseña encriptada la cual la vot a guardad en la base de dato s

package com.project.logincafeteria.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordGeneratorRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        String rawPassword = "perico"; // Cambia aquí tu contraseña
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Password hash para '" + rawPassword + "': " + encodedPassword);
    }
}
