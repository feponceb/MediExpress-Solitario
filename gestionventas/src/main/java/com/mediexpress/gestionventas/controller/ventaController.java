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

import com.mediexpress.gestionventas.model.venta;
import com.mediexpress.gestionventas.service.ventaService;

@RestController
@RequestMapping("/ventas")
public class ventaController {

    @Autowired
    private ventaService VentaService;

    @GetMapping
    public List<venta> getVentas() {
        return VentaService.getVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<venta> getVenta(@PathVariable Long id) {
        return ResponseEntity.ok(VentaService.getVenta(id));
    }

    @PostMapping
    public ResponseEntity<venta> saveVenta(@RequestBody venta v) {
        venta nueva = VentaService.saveVenta(v);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<venta> updateVenta(@PathVariable Long id, @RequestBody venta nuevo) {
        venta actualizada = VentaService.updateVenta(id, nuevo);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        VentaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }

}
