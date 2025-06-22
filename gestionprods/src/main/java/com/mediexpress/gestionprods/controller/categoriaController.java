package com.mediexpress.gestionprods.controller;

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

import com.mediexpress.gestionprods.model.categoria;
import com.mediexpress.gestionprods.service.categoriaService;

@RestController
@RequestMapping("/api/categoria")
public class categoriaController {

    @Autowired
    private categoriaService CategoriaService;

    // Obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<categoria>> getCategorias() {
        return ResponseEntity.ok(CategoriaService.getCategorias());
    }

    // Obtener categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<categoria> getCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(CategoriaService.getCategoria(id));
    }

    // Crear nueva categoría
    @PostMapping
    public ResponseEntity<categoria> saveCategoria(@RequestBody categoria c) {
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaService.saveCategoria(c));
    }

    // Actualizar categoría
    @PutMapping("/{id}")
    public ResponseEntity<categoria> updateCategoria(@PathVariable Long id, @RequestBody categoria nueva) {
        return ResponseEntity.ok(CategoriaService.updateCategoria(id, nueva));
    }

    // Eliminar categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        CategoriaService.deleteCategoria(id);
        return ResponseEntity.ok("Categoría eliminada correctamente");
    }

}
