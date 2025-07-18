package com.retailmax.notifications.repository;

import com.retailmax.notifications.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
} 