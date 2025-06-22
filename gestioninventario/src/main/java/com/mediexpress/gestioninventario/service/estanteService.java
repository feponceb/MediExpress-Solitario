package com.mediexpress.gestioninventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestioninventario.model.estante;
import com.mediexpress.gestioninventario.repository.estanteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class estanteService {

    @Autowired
    private estanteRepository EstanteRepository;

    public List<estante> getAllEstantes() {
        return EstanteRepository.findAll();
    }

    public estante getEstante(Long id) {
        return EstanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estante no encontrado"));
    }

    public estante saveEstante(estante e) {
        return EstanteRepository.save(e);
    }

    public estante updateEstante(Long id, estante nuevo) {
        estante existente = getEstante(id);
        existente.setNombreEstante(nuevo.getNombreEstante());
        return EstanteRepository.save(existente);
    }

    public void deleteEstante(Long id) {
        getEstante(id);
        EstanteRepository.deleteById(id);
    }

}
