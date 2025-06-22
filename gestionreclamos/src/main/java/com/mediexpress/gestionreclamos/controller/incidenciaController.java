package com.mediexpress.gestionreclamos.controller;

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

import com.mediexpress.gestionreclamos.model.incidencia;
import com.mediexpress.gestionreclamos.service.incidenciaService;

@RestController
@RequestMapping("/incidencia")
public class incidenciaController {

    @Autowired
    private incidenciaService incidenciaService;

    @GetMapping
    public List<incidencia> getIncidencias() {
        return incidenciaService.getIncidencias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<incidencia> getIncidencia(@PathVariable Long id) {
        return ResponseEntity.ok(incidenciaService.getIncidencia(id));
    }

    @PostMapping
    public ResponseEntity<incidencia> saveIncidencia(@RequestBody incidencia i) {
        incidencia nuevo = incidenciaService.saveIncidencia(i);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<incidencia> updateIncidencia(@PathVariable Long id, @RequestBody incidencia i) {
        incidencia actualizado = incidenciaService.updateIncidencia(id, i);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncidencia(@PathVariable Long id) {
        incidenciaService.deleteIncidencia(id);
        return ResponseEntity.noContent().build();
    }

}
