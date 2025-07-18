package com.retailmax.notifications.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.retailmax.notifications.model.Promocion;
import com.retailmax.notifications.service.PromocionService;

import java.util.List;
import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/promociones")
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @GetMapping
    public List<Promocion> listar() {
        return promocionService.listarTodas();
    }

    @PostMapping
    public Promocion crear(@RequestBody Promocion promocion) {
        return promocionService.save(promocion);
    }
    @GetMapping("/{id}")
    public EntityModel<Promocion> buscarPorId(@PathVariable Long id) {
        Promocion promocion = promocionService.buscarPorId(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        EntityModel<Promocion> resource = EntityModel.of(promocion);
        resource.add(linkTo(methodOn(PromocionController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(PromocionController.class).listar()).withRel("all"));

        return resource;
    }
}