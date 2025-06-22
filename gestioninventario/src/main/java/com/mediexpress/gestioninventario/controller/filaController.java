package com.mediexpress.gestioninventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediexpress.gestioninventario.model.fila;
import com.mediexpress.gestioninventario.service.filaService;

@RestController
@RequestMapping("/filas")
public class filaController {

    @Autowired
    private filaService filaService;

    @GetMapping
    public List<fila> getAll() {
        return filaService.getAllFilas();
    }

    @GetMapping("/{id}")
    public fila getOne(@PathVariable Long id) {
        return filaService.getFila(id);
    }

    @PostMapping
    public fila save(@RequestBody fila f) {
        return filaService.saveFila(f);
    }

    @PutMapping("/{id}")
    public fila update(@PathVariable Long id, @RequestBody fila f) {
        return filaService.updateFila(id, f);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        filaService.deleteFila(id);
        return ResponseEntity.ok("Fila eliminada");
    }

}
