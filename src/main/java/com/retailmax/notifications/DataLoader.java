package com.retailmax.notifications;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.retailmax.notifications.model.Promocion;
import com.retailmax.notifications.repository.PromocionRepository;
import com.retailmax.notifications.model.Notificacion;
import com.retailmax.notifications.repository.NotificacionRepository;
import java.time.LocalDateTime;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PromocionRepository promocionRepository;
    @Autowired(required = false)
    private NotificacionRepository notificacionRepository;

    void setPromocionRepository(PromocionRepository promocionRepository) {
        this.promocionRepository = promocionRepository;
    }
    void setNotificacionRepository(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();
        // Generar 10 promociones de ejemplo
        for (int i = 0; i < 10; i++) {
            Promocion promocion = new Promocion();
            promocion.setTipo("gmail");
            promocion.setFechaEnvio(LocalDateTime.now().minusDays(random.nextInt(30)));
            promocion.setResultadoEnvio(random.nextBoolean() ? "Enviado" : "Fallido");
            promocionRepository.save(promocion);
        }
        System.out.println("✅ Promociones de ejemplo cargadas exitosamente en modo desarrollo");

        // Generar 10 notificaciones de ejemplo si el repositorio existe
        if (notificacionRepository != null) {
            for (int i = 0; i < 10; i++) {
                Notificacion notificacion = new Notificacion();
                notificacion.setTipo(random.nextBoolean() ? "producto" : "promocion");
                notificacion.setMensaje("Mensaje de ejemplo " + (i + 1));
                notificacion.setIdUsuarioDestinatario((long) (1 + random.nextInt(5)));
                notificacion.setEstado(random.nextBoolean() ? "ENVIADA" : "PENDIENTE");
                notificacion.setFechaEnvio(LocalDateTime.now().minusDays(random.nextInt(30)));
                notificacionRepository.save(notificacion);
            }
            System.out.println("✅ Notificaciones de ejemplo cargadas exitosamente en modo desarrollo");
        }
    }
}