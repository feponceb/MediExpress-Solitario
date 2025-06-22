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

import com.mediexpress.gestionlogistica.model.encargo;
import com.mediexpress.gestionlogistica.service.encargoService;

@RestController
@RequestMapping("/encargos")
public class encargoController {

    @Autowired
    private encargoService EncargoService;

    @GetMapping
    public List<encargo> getAllEncargos() {
        return EncargoService.getAllEncargos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<encargo> getEncargo(@PathVariable Long id) {
        encargo e = EncargoService.getEncargo(id);
        return ResponseEntity.ok(e);
    }

    @PostMapping
    public ResponseEntity<encargo> saveEncargo(@RequestBody encargo e) {
        encargo nuevo = EncargoService.saveEncargo(e);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<encargo> updateEncargo(@PathVariable Long id, @RequestBody encargo nuevo) {
        encargo actualizado = EncargoService.updateEncargo(id, nuevo);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEncargo(@PathVariable Long id) {
        EncargoService.deleteEncargo(id);
        return ResponseEntity.noContent().build();
    }

}
