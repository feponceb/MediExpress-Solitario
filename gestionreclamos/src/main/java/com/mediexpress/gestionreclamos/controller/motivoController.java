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

import com.mediexpress.gestionreclamos.model.motivo;
import com.mediexpress.gestionreclamos.service.motivoService;

@RestController
@RequestMapping("/motivos")
public class motivoController {

    @Autowired
    private motivoService motivoService;

    @GetMapping
    public List<motivo> getMotivos() {
        return motivoService.getMotivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<motivo> getMotivo(@PathVariable Long id) {
        return ResponseEntity.ok(motivoService.getMotivo(id));
    }

    @PostMapping
    public ResponseEntity<motivo> saveMotivo(@RequestBody motivo m) {
        motivo nuevo = motivoService.saveMotivo(m);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<motivo> updateMotivo(@PathVariable Long id, @RequestBody motivo m) {
        motivo actualizado = motivoService.updateMotivo(id, m);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotivo(@PathVariable Long id) {
        motivoService.deleteMotivo(id);
        return ResponseEntity.noContent().build();
    }

}
