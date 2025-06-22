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

import com.mediexpress.gestionprods.model.producto;
import com.mediexpress.gestionprods.service.productoService;

@RestController
@RequestMapping("/api/productos")
public class productoController {

    @Autowired
    private productoService ProductoService;

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<producto>> getProductos() {
        return ResponseEntity.ok(ProductoService.getProductos());
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<producto> getProducto(@PathVariable Long id) {
        return ResponseEntity.ok(ProductoService.getProducto(id));
    }

    // Crear nuevo producto
    @PostMapping
    public ResponseEntity<producto> saveProducto(@RequestBody producto p) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductoService.saveProducto(p));
    }

    // Actualizar producto existente
    @PutMapping("/{id}")
    public ResponseEntity<producto> updateProducto(@PathVariable Long id, @RequestBody producto nuevoProducto) {
        return ResponseEntity.ok(ProductoService.updateProducto(id, nuevoProducto));
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        ProductoService.deleteProducto(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }
}
