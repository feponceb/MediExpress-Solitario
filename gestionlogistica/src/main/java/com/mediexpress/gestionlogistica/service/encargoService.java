package com.mediexpress.gestionlogistica.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionlogistica.model.encargo;
import com.mediexpress.gestionlogistica.repository.encargoRepository;
import com.mediexpress.gestionlogistica.webclient.VentaClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class encargoService {

    @Autowired
    private encargoRepository EncargoRepository;  

    @Autowired
    private VentaClient ventaClient;

    public List<encargo> getAllEncargos() {
        return EncargoRepository.findAll();
    }

    public encargo getEncargo(Long id) {
        return EncargoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Encargo no encontrado"));
    }

    public encargo saveEncargo(encargo e) {
    // Validar existencia de la venta
    Map<String, Object> venta = ventaClient.obtenerVentaPorId(e.getIdOrden());
    if (venta == null || venta.isEmpty()) {
            throw new RuntimeException("Venta no encontrada, no se puede crear encargo");
        }

        return EncargoRepository.save(e);
    }

    public encargo updateEncargo(Long id, encargo nuevo) {
    encargo existente = getEncargo(id);

    // Validar existencia de la venta referenciada en la actualización
    Map<String, Object> venta = ventaClient.obtenerVentaPorId(nuevo.getIdOrden());
    if (venta == null || venta.isEmpty()) {
        throw new RuntimeException("Venta no encontrada, no se puede actualizar encargo");
    }

        existente.setIdOrden(nuevo.getIdOrden());
        existente.setEstado(nuevo.getEstado());
        existente.setFechaSalida(nuevo.getFechaSalida());
        existente.setFechaEntregaEstimada(nuevo.getFechaEntregaEstimada());
        existente.setFechaFinalizada(nuevo.getFechaFinalizada());
        existente.setTracking(nuevo.getTracking());

        return EncargoRepository.save(existente);
    }

    public void deleteEncargo(Long id) {
        getEncargo(id); // Para lanzar error si no existe
        EncargoRepository.deleteById(id);
    }

}
