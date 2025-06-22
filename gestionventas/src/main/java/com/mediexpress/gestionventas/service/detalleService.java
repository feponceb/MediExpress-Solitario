package com.mediexpress.gestionventas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionventas.model.detalle;
import com.mediexpress.gestionventas.repository.detalleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class detalleService {

     @Autowired
    private detalleRepository DetalleRepository;

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

    public detalle saveDetalle(detalle d) {
        return DetalleRepository.save(d);
    }

    public detalle updateDetalle(Long id, detalle nuevo) {
        detalle existente = getDetalle(id);
        existente.setCantidad(nuevo.getCantidad());
        existente.setPrecioUnit(nuevo.getPrecioUnit());
        existente.setSubtotal(nuevo.getSubtotal());
        existente.setVenta(nuevo.getVenta());
        existente.setIdProducto(nuevo.getIdProducto());
        return DetalleRepository.save(existente);
    }

    public void deleteDetalle(Long id) {
        DetalleRepository.deleteById(id);
    }

}
