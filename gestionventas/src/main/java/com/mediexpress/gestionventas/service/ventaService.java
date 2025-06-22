package com.mediexpress.gestionventas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionventas.model.venta;
import com.mediexpress.gestionventas.repository.ventaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ventaService {

    @Autowired
    private ventaRepository VentaRepository;

    public List<venta> getVentas() {
        return VentaRepository.findAll();
    }

    public venta getVenta(Long id) {
        return VentaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    public venta saveVenta(venta v) {
        return VentaRepository.save(v);
    }

    public venta updateVenta(Long id, venta nuevo) {
        venta existente = getVenta(id);
        existente.setFechaVenta(nuevo.getFechaVenta());
        existente.setTotal(nuevo.getTotal());
        existente.setIdUsuario(nuevo.getIdUsuario());
        return VentaRepository.save(existente);
    }

    public void deleteVenta(Long id) {
        VentaRepository.deleteById(id);
    }

}
