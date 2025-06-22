package com.mediexpress.gestionlogistica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionlogistica.model.encargo;
import com.mediexpress.gestionlogistica.repository.encargoRepository;
import com.mediexpress.gestionlogistica.repository.estadoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class encargoService {

    @Autowired
    private encargoRepository EncargoRepository;

    @Autowired
    private estadoRepository EstadoRepository;

    public List<encargo> getAllEncargos() {
        return EncargoRepository.findAll();
    }

    public encargo getEncargo(Long id) {
        return EncargoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Encargo no encontrado"));
    }

    public encargo saveEncargo(encargo e) {
        // Validar existencia del estado
        EstadoRepository.findById(e.getEstadoR().getIdEstado())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        return EncargoRepository.save(e);
    }

    public encargo updateEncargo(Long id, encargo nuevo) {
        encargo existente = getEncargo(id);

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
