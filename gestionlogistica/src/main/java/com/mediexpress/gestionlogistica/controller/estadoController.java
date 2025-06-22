package com.mediexpress.gestionlogistica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediexpress.gestionlogistica.model.estado;
import com.mediexpress.gestionlogistica.service.estadoService;

@RestController
@RequestMapping("/estados")
public class estadoController {

    @Autowired
    private estadoService EstadoService;

    @GetMapping
    public List<estado> getAllEstados() {
        return EstadoService.getAllEstados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<estado> getEstado(@PathVariable Long id) {
        estado e = EstadoService.getEstado(id);
        return ResponseEntity.ok(e);
    }

    @PostMapping
    public ResponseEntity<estado> saveEstado(@RequestBody estado e) {
        estado nuevo = EstadoService.saveEstado(e);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<estado> updateEstado(@PathVariable Long id, @RequestBody estado nuevo) {
        estado actualizado = EstadoService.updateEstado(id, nuevo);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable Long id) {
        EstadoService.deleteEstado(id);
        return ResponseEntity.noContent().build();
    }

}
