package com.mediexpress.gestionreclamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionreclamos.model.motivo;
import com.mediexpress.gestionreclamos.repository.motivoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class motivoService {

    @Autowired
    private motivoRepository motivoRepository;

    public List<motivo> getMotivos() {
        return motivoRepository.findAll();
    }

    public motivo getMotivo(Long id) {
        return motivoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Motivo no encontrado"));
    }

    public motivo saveMotivo(motivo m) {
        return motivoRepository.save(m);
    }

    public motivo updateMotivo(Long id, motivo nuevo) {
        motivo existente = getMotivo(id);
        existente.setNombreMotiv(nuevo.getNombreMotiv());
        return motivoRepository.save(existente);
    }

    public void deleteMotivo(Long id) {
        motivoRepository.deleteById(id);
    }

}
