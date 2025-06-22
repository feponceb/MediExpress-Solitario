package com.mediexpress.gestionresenias.controller;

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

import com.mediexpress.gestionresenias.model.resena;
import com.mediexpress.gestionresenias.service.resenaService;

@RestController
@RequestMapping("/resena")
public class resenaController {

    @Autowired
    private resenaService resenaService;

    @GetMapping
    public List<resena> getResenas() {
        return resenaService.getResenas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<resena> getResena(@PathVariable Long id) {
        return ResponseEntity.ok(resenaService.getResena(id));
    }

    @GetMapping("/producto/{idProducto}")
    public List<resena> getResenasByProducto(@PathVariable Long idProducto) {
        return resenaService.getResenasByProducto(idProducto);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<resena> getResenasByUsuario(@PathVariable Long idUsuario) {
        return resenaService.getResenasByUsuario(idUsuario);
    }

    @PostMapping
    public ResponseEntity<resena> saveResena(@RequestBody resena r) {
        resena nueva = resenaService.saveResena(r);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<resena> updateResena(@PathVariable Long id, @RequestBody resena r) {
        resena actualizada = resenaService.updateResena(id, r);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResena(@PathVariable Long id) {
        resenaService.deleteResena(id);
        return ResponseEntity.noContent().build();
    }

}
