package com.retailmax.notifications.controller;

import com.retailmax.notifications.model.Notificacion;
import com.retailmax.notifications.service.NotificacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {
    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Notificacion> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Notificacion> crear(@RequestBody Notificacion notificacion) {
        Notificacion creada = service.guardar(notificacion);
        return ResponseEntity.status(201).body(creada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerPorId(@PathVariable Long id) {
        Optional<Notificacion> notificacion = service.listarTodas().stream().filter(n -> n.getId().equals(id)).findFirst();
        return notificacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> actualizar(@PathVariable Long id, @RequestBody Notificacion notificacion) {
        Optional<Notificacion> existente = service.listarTodas().stream().filter(n -> n.getId().equals(id)).findFirst();
        if (existente.isPresent()) {
            notificacion.setId(id);
            Notificacion actualizada = service.guardar(notificacion);
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Optional<Notificacion> existente = service.listarTodas().stream().filter(n -> n.getId().equals(id)).findFirst();
        if (existente.isPresent()) {
            service.listarTodas().removeIf(n -> n.getId().equals(id));
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{idUsuarioDestinatario}")
    public List<Notificacion> listarPorUsuarioDestinatario(@PathVariable Long idUsuarioDestinatario) {
        return service.listarTodas().stream()
                .filter(n -> n.getIdUsuarioDestinatario() != null && n.getIdUsuarioDestinatario().equals(idUsuarioDestinatario))
                .toList();
    }
} 