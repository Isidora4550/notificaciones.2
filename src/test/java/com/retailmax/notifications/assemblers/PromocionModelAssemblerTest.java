package com.retailmax.notifications.assemblers;

import com.retailmax.notifications.model.Promocion;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.assertj.core.api.Assertions.assertThat;

class PromocionModelAssemblerTest {

    @Test
    void testToModel() {
        Promocion promocion = new Promocion();
        promocion.setId(1L);
        promocion.setTipo("gmail");
        promocion.setResultadoEnvio("OK");

        PromocionModelAssembler assembler = new PromocionModelAssembler();
        EntityModel<Promocion> model = assembler.toModel(promocion);

        assertThat(model.getContent()).isEqualTo(promocion);
        assertThat(model.getLinks()).isNotEmpty();
        assertThat(model.getLink("self")).isPresent();
        assertThat(model.getLink("promociones")).isPresent();
    }
} 