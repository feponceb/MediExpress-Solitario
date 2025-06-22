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

import com.mediexpress.gestioninventario.model.estante;
import com.mediexpress.gestioninventario.service.estanteService;

@RestController
@RequestMapping("/estantes")
public class estanteController {

    @Autowired
    private estanteService estanteService;

    @GetMapping
    public List<estante> getAll() {
        return estanteService.getAllEstantes();
    }

    @GetMapping("/{id}")
    public estante getOne(@PathVariable Long id) {
        return estanteService.getEstante(id);
    }

    @PostMapping
    public estante save(@RequestBody estante e) {
        return estanteService.saveEstante(e);
    }

    @PutMapping("/{id}")
    public estante update(@PathVariable Long id, @RequestBody estante e) {
        return estanteService.updateEstante(id, e);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        estanteService.deleteEstante(id);
        return ResponseEntity.ok("Estante eliminado");
    }

}
