package com.mediexpress.gestionresenias.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionresenias.model.resena;
import com.mediexpress.gestionresenias.repository.resenaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class resenaService {

    @Autowired
    private resenaRepository ResenaRepository;

    public List<resena> getResenas() {
        return ResenaRepository.findAll();
    }

    public resena getResena(Long id) {
        return ResenaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
    }

    public List<resena> getResenasByProducto(Long idProducto) {
        return ResenaRepository.findByIdProducto(idProducto);
    }

    public List<resena> getResenasByUsuario(Long idUsuario) {
        return ResenaRepository.findByIdUsuario(idUsuario);
    }

    public resena saveResena(resena r) {
        return ResenaRepository.save(r);
    }

    public resena updateResena(Long id, resena nuevo) {
        resena existente = getResena(id);
        existente.setComentario(nuevo.getComentario());
        existente.setCalificacion(nuevo.getCalificacion());
        existente.setIdUsuario(nuevo.getIdUsuario());
        existente.setIdProducto(nuevo.getIdProducto());
        existente.setFecha(nuevo.getFecha());
        return ResenaRepository.save(existente);
    }

    public void deleteResena(Long id) {
        ResenaRepository.deleteById(id);
    }

}
