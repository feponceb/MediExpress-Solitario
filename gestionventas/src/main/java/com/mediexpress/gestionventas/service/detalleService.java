package com.mediexpress.gestionventas.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionventas.model.detalle;
import com.mediexpress.gestionventas.repository.detalleRepository;
import com.mediexpress.gestionventas.webclient.ProductoClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class detalleService {

     @Autowired
    private detalleRepository DetalleRepository;

    @Autowired
    private ProductoClient productoClient;

    public List<detalle> getDetalles() {
        return DetalleRepository.findAll();
    }

    public detalle getDetalle(Long id) {
        return DetalleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
    }

    public List<detalle> getDetallesByVenta(Long idVenta) {
        return DetalleRepository.findByVenta_IdVenta(idVenta);
    }

    public detalle saveDetalle(detalle v) {
        Map<String,Object> producto = productoClient.obtenerProductoId(v.getIdProducto());
        if (producto == null || producto.isEmpty()){
            throw new RuntimeException("Producto no encontrado, incapaz de crear detalle");
        }
        return DetalleRepository.save(v);
    }

    public detalle updateDetalle(Long id, detalle nuevo) {
        Map<String, Object> producto = productoClient.obtenerProductoId(nuevo.getIdProducto());
        if (producto == null || producto.isEmpty()) {
        throw new RuntimeException("Producto no encontrado, incapaz de actualizar detalle");
    }
        
        detalle existente = getDetalle(id);
        existente.setCantidad(nuevo.getCantidad());
        existente.setPrecioUnit(nuevo.getPrecioUnit());
        existente.setSubtotal(nuevo.getSubtotal());
        existente.setVenta(nuevo.getVenta());
        existente.setIdProducto(nuevo.getIdProducto());
        return DetalleRepository.save(existente);
    }

    public void deleteDetalle(Long id) {
        getDetalle(id);
        DetalleRepository.deleteById(id);
    }

}
