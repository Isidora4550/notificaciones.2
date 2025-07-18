package com.retailmax.notifications.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailmax.notifications.model.Notificacion;
import com.retailmax.notifications.service.NotificacionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificacionController.class)
class NotificacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificacionService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListar() throws Exception {
        Mockito.when(service.listarTodas()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/notificaciones"))
                .andExpect(status().isOk());
    }

    @Test
    void testCrear() throws Exception {
        Notificacion notif = new Notificacion();
        notif.setTipo("producto");
        notif.setMensaje("Mensaje de prueba");
        notif.setIdUsuarioDestinatario(1L);
        notif.setEstado("ENVIADA");
        notif.setFechaEnvio(LocalDateTime.now());

        Mockito.when(service.guardar(Mockito.any())).thenReturn(notif);

        mockMvc.perform(post("/api/notificaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(notif)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.mensaje").value("Mensaje de prueba"));
    }
} 