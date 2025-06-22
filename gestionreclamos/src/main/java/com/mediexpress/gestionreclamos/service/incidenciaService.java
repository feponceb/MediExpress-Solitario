package com.mediexpress.gestionreclamos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionreclamos.model.incidencia;
import com.mediexpress.gestionreclamos.repository.incidenciaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class incidenciaService {

    @Autowired
    private incidenciaRepository incidenciaRepository;

    public List<incidencia> getIncidencias() {
        return incidenciaRepository.findAll();
    }

    public incidencia getIncidencia(Long id) {
        return incidenciaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
    }

    public incidencia saveIncidencia(incidencia i) {
        return incidenciaRepository.save(i);
    }

    public incidencia updateIncidencia(Long id, incidencia nuevo) {
        incidencia existente = getIncidencia(id);
        existente.setComentario(nuevo.getComentario());
        existente.setMotivo(nuevo.getMotivo());
        existente.setTicket(nuevo.getTicket());
        existente.setIdVenta(nuevo.getIdVenta());
        return incidenciaRepository.save(existente);
    }

    public void deleteIncidencia(Long id) {
        incidenciaRepository.deleteById(id);
    }

}
