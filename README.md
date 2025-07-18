# Ordenes

## Contexto del Proyecto

Este proyecto es un **microservicio de notificaciones** desarrollado para el ecosistema RetailMax. Su propósito es gestionar el envío, almacenamiento y administración de notificaciones a los usuarios, así como la gestión de usuarios, promociones, pedidos y preferencias de notificación. El microservicio está diseñado para ser escalable, seguro y fácilmente integrable con otros sistemas de retail y e-commerce.

### Propósito y Alcance
- Centralizar la gestión de notificaciones (email, SMS, etc.) para usuarios del sistema.
- Permitir a los usuarios configurar sus preferencias de notificación (canal, habilitación, etc.).
- Registrar y consultar el historial de notificaciones enviadas.
- Integrar la gestión de usuarios, promociones y pedidos para un flujo completo de comunicación.

### Módulos principales

- **Notificaciones:** Creación, consulta, actualización, eliminación y filtrado de notificaciones por usuario.
- **Promociones:** Gestión de promociones y su envío como notificaciones.

- **Preferencias de notificación:** Configuración personalizada por usuario y canal.

### Tecnologías utilizadas
- **Java 21** y **Spring Boot 3.3.11**
- **Spring Data JPA** (persistencia)
- **Spring HATEOAS** (enlaces hipermedia en la API)
- **Springdoc OpenAPI/Swagger UI** (documentación interactiva)
- **Oracle Database** (producción) y **H2** (desarrollo/pruebas)
- **Docker** (despliegue y orquestación)
- **JUnit, Mockito** (pruebas unitarias e integración)
- **Lombok** (reducción de boilerplate)

### Arquitectura general
- Arquitectura basada en microservicios.
- API RESTful con endpoints versionados y documentados.
- Separación en capas: controlador, servicio, repositorio y modelo.
- Soporte para despliegue local y en la nube (Docker, Oracle Cloud).

### Casos de uso clave
- Un usuario puede recibir notificaciones sobre promociones, pedidos u otros eventos relevantes.
- Los administradores pueden consultar el historial de notificaciones enviadas a cada usuario.
- Los usuarios pueden configurar si desean recibir notificaciones y por qué canal.
- El sistema puede integrarse con otros microservicios para disparar notificaciones automáticas.

### Características principales
- API RESTful para usuarios, notificaciones, promociones, pedidos y preferencias
- Gestión de notificaciones: crear, consultar, actualizar, eliminar y filtrar por usuario
- Preferencias de notificación configurables por usuario
- Documentación interactiva con Swagger UI
- Pruebas unitarias y de integración
- Soporte para HATEOAS
- Despliegue con Docker

## Docker configuracion 

1. Crear app.jar
```
.\mvnw clean package
```

2. Iniciar el proyecto
```
docker compose up
```