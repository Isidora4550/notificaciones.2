package com.retailmax.notifications;

import com.retailmax.notifications.repository.PromocionRepository;
import com.retailmax.notifications.repository.NotificacionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
public class DataLoaderIntegrationTest {

    @Autowired
    private PromocionRepository promocionRepository;
    @Autowired(required = false)
    private NotificacionRepository notificacionRepository;

    @Test
    void testPromocionesCargadas() {
        long count = promocionRepository.count();
        assertTrue(count >= 10, "Se esperaban al menos 10 promociones de ejemplo");
    }

    @Test
    void testNotificacionesCargadas() {
        if (notificacionRepository != null) {
            long count = notificacionRepository.count();
            assertTrue(count >= 10, "Se esperaban al menos 10 notificaciones de ejemplo");
        }
    }
} 