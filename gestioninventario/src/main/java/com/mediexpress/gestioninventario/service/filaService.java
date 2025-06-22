package com.mediexpress.gestioninventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestioninventario.model.fila;
import com.mediexpress.gestioninventario.repository.estanteRepository;
import com.mediexpress.gestioninventario.repository.filaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class filaService {

    @Autowired
    private filaRepository FilaRepository;

    @Autowired
    private estanteRepository EstanteRepository;

    public List<fila> getAllFilas() {
        return FilaRepository.findAll();
    }

    public fila getFila(Long id) {
        return FilaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fila no encontrada"));
    }

    public fila saveFila(fila f) {
        EstanteRepository.findById(f.getEstante().getIdEstante())
                .orElseThrow(() -> new RuntimeException("Estante no encontrado"));
        return FilaRepository.save(f);
    }

    public fila updateFila(Long id, fila nueva) {
        fila existente = getFila(id);
        existente.setNombreFila(nueva.getNombreFila());
        existente.setEstante(nueva.getEstante());
        return FilaRepository.save(existente);
    }

    public void deleteFila(Long id) {
        getFila(id);
        FilaRepository.deleteById(id);
    }

}
