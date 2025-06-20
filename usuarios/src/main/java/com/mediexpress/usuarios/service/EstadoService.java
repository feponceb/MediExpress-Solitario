package com.mediexpress.usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.usuarios.model.Estado;
import com.mediexpress.usuarios.repository.EstadoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    //traer todos los roles
    public List<Estado> getEstados(){
        return estadoRepository.findAll();
    }

    //buscar roles por id
    public Estado getEstado(Long id){
        return estadoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Estado no encontrado"));
    }

    //crear roles
    public Estado saveRol(Estado estado){
        return estadoRepository.save(estado);
    }

    //deletear
    public void deleteEstado(Long id){
        estadoRepository.deleteById(id);
    }

}
