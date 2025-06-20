package com.mediexpress.usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.usuarios.model.Rol;
import com.mediexpress.usuarios.repository.RolRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {
    @Autowired
    private RolRepository rolRepository;
    
    
    //traer todos los roles
    public List<Rol> getRoles(){
        return rolRepository.findAll();
    }

    //buscar roles por id
    public Rol getRol(Long id){
        return rolRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Rol no encontrado"));
    }

    //crear roles
    public Rol saveRol(Rol rol){
        return rolRepository.save(rol);
    }

    //deletear
    public void deleteRol(Long id){
        rolRepository.deleteById(id);
    }

    //updateo
    public Rol updateRol(Long id, Rol nuevoRol) {
    Rol existente = rolRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    existente.setNombreRol(nuevoRol.getNombreRol());
    return rolRepository.save(existente);
}

}
