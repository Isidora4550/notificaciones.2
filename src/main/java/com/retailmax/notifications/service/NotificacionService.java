package com.retailmax.notifications.service;

import com.retailmax.notifications.model.Notificacion;
import com.retailmax.notifications.repository.NotificacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionService {
    private final NotificacionRepository repository;

    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    public List<Notificacion> listarTodas() {
        return repository.findAll();
    }

    public Notificacion guardar(Notificacion notificacion) {
        return repository.save(notificacion);
    }
} 