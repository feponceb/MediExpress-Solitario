package com.mediexpress.gestionlogistica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionlogistica.model.estado;
import com.mediexpress.gestionlogistica.repository.estadoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class estadoService {

    @Autowired
    private estadoRepository EstadoRepository;

    public List<estado> getAllEstados() {
        return EstadoRepository.findAll();
    }

    public estado getEstado(Long id) {
        return EstadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
    }

    public estado saveEstado(estado e) {
        return EstadoRepository.save(e);
    }

    public estado updateEstado(Long id, estado nuevo) {
        estado existente = getEstado(id);
        existente.setNombreEstado(nuevo.getNombreEstado());
        return EstadoRepository.save(existente);
    }

    public void deleteEstado(Long id) {
        getEstado(id);
        EstadoRepository.deleteById(id);
    }

}
