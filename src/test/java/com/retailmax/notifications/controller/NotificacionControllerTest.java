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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    void testActualizar() throws Exception {
        Notificacion notif = new Notificacion();
        notif.setId(1L);
        notif.setTipo("producto");
        notif.setMensaje("Mensaje actualizado");
        notif.setIdUsuarioDestinatario(1L);
        notif.setEstado("ENVIADA");

        Mockito.when(service.listarTodas()).thenReturn(Collections.singletonList(notif));
        Mockito.when(service.guardar(Mockito.any())).thenReturn(notif);

        mockMvc.perform(put("/api/notificaciones/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(notif)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Mensaje actualizado"));
    }

    @Test
    void testEliminar() throws Exception {
        Notificacion notif = new Notificacion();
        notif.setId(1L);

        Mockito.when(service.listarTodas()).thenReturn(Collections.singletonList(notif));
        Mockito.doNothing().when(service).eliminar(1L);

        mockMvc.perform(delete("/api/notificaciones/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testObtenerPorId() throws Exception {
        Notificacion notif = new Notificacion();
        notif.setId(1L);

        Mockito.when(service.listarTodas()).thenReturn(Collections.singletonList(notif));

        mockMvc.perform(get("/api/notificaciones/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testListarPorUsuarioDestinatario() throws Exception {
        Notificacion notif = new Notificacion();
        notif.setIdUsuarioDestinatario(1L);

        Mockito.when(service.listarTodas()).thenReturn(Collections.singletonList(notif));

        mockMvc.perform(get("/api/notificaciones/usuario/1"))
                .andExpect(status().isOk());
    }
} 