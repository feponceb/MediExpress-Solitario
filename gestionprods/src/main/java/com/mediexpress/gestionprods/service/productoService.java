package com.mediexpress.gestionprods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionprods.model.producto;
import com.mediexpress.gestionprods.repository.productoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class productoService {
    
    @Autowired
    private productoRepository ProductoRepository;

    // Listar todos los productos
    public List<producto> getProductos() {
        return ProductoRepository.findAll();
    }

    // Obtener producto por ID
    public producto getProducto(Long id) {
        return ProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    // Crear producto
    public producto saveProducto(producto p) {
        return ProductoRepository.save(p);
    }

    // Eliminar producto
    public void deleteProducto(Long id) {
        ProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        ProductoRepository.deleteById(id);
    }

    // Actualizar producto
    public producto updateProducto(Long id, producto nuevoProducto) {
        producto existente = ProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existente.setNombre(nuevoProducto.getNombre());
        existente.setDescripcion(nuevoProducto.getDescripcion());
        existente.setStock(nuevoProducto.getStock());
        existente.setPrecio(nuevoProducto.getPrecio());
        existente.setUnidad(nuevoProducto.getUnidad());
        existente.setFecha_exp(nuevoProducto.getFecha_exp());
        existente.setCategoria(nuevoProducto.getCategoria());

        return ProductoRepository.save(existente);
    }

}
