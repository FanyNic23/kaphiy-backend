# Kaphiy Backend

API REST para la gestión de una cafetería.

## Tecnologías

- Java 21
- Spring Boot 3.5.0
- Spring Web
- Spring Data JPA
- Spring Security
- JSON Web Token (JWT)
- MySQL
- Maven

## Funcionalidades

- Registro e inicio de sesión de usuarios
- Autenticación mediante JWT
- Gestión de usuarios y roles
- Gestión de categorías
- Gestión de productos
- Validación de datos
- Control de acceso para administradores

## Requisitos

- JDK 21
- MySQL
- Git

## Configuración de la base de datos

Crea una base de datos llamada `cafeteria` en MySQL:

```sql
CREATE DATABASE cafeteria;
```

La configuración local se encuentra en `src/main/resources/application.properties`.

Ajusta el usuario y la contraseña de MySQL según tu instalación:

```properties
spring.datasource.username=root
spring.datasource.password=tu_contraseña
```

## Configuración de JWT

La clave JWT se obtiene desde la variable de entorno `JWT_SECRET`. En PowerShell puedes configurarla así:

```powershell
$env:JWT_SECRET="coloca-aqui-una-clave-larga-y-segura"
```

No publiques claves JWT, contraseñas ni otros datos sensibles en GitHub.

## Ejecución

Clona el repositorio y entra en la carpeta del proyecto:

```bash
git clone https://github.com/FanyNic23/kaphiy-backend.git
cd kaphiy-backend
```

En Windows, inicia la aplicación con:

```bash
mvnw.cmd spring-boot:run
```

La API estará disponible en:

```text
http://localhost:9090
```

## Pruebas

Para ejecutar las pruebas automatizadas:

```bash
mvnw.cmd test
```

## Estructura principal

- `controller`: endpoints de la API
- `service`: lógica de negocio
- `repo`: repositorios para acceso a datos
- `model`: entidades de la base de datos
- `dtos`: objetos para solicitudes y respuestas
- `security`: autenticación y autorización JWT
- `exception`: manejo global de errores

## Autor

Fany Nic23
