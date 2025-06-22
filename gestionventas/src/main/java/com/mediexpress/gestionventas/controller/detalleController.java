package com.mediexpress.gestionventas.controller;

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

import com.mediexpress.gestionventas.model.detalle;
import com.mediexpress.gestionventas.service.detalleService;

@RestController
@RequestMapping("/detalle")
public class detalleController {

    @Autowired
    private detalleService DetalleService;

    @GetMapping
    public List<detalle> getDetalles() {
        return DetalleService.getDetalles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<detalle> getDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(DetalleService.getDetalle(id));
    }

    @GetMapping("/venta/{idVenta}")
    public List<detalle> getDetallesByVenta(@PathVariable Long idVenta) {
        return DetalleService.getDetallesByVenta(idVenta);
    }

    @PostMapping
    public ResponseEntity<detalle> saveDetalle(@RequestBody detalle d) {
        detalle nuevo = DetalleService.saveDetalle(d);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<detalle> updateDetalle(@PathVariable Long id, @RequestBody detalle nuevo) {
        detalle actualizado = DetalleService.updateDetalle(id, nuevo);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalle(@PathVariable Long id) {
        DetalleService.deleteDetalle(id);
        return ResponseEntity.noContent().build();
    }

}
