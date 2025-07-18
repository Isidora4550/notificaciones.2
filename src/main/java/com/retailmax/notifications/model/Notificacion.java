package com.retailmax.notifications.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificacion")
@Data
@NoArgsConstructor
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Origen de la notificación: producto, pedido, etc.
    private String tipo;

    private String mensaje;

    // ID del usuario destinatario
    private Long idUsuarioDestinatario;

    // Estado de la notificación (ENVIADA, PENDIENTE, ERROR, etc.)
    private String estado;

    // Fecha y hora de envío
    private LocalDateTime fechaEnvio;
} 