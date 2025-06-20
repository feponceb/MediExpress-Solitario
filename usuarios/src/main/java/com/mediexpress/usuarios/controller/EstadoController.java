package com.mediexpress.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediexpress.usuarios.model.Estado;
import com.mediexpress.usuarios.service.EstadoService;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {
    @Autowired
    public EstadoService estadoService;

    //agarrar todo
    @GetMapping
    public ResponseEntity<List<Estado>> obtenerEstados(){
        List<Estado> estados = estadoService.getEstados();
        if(estados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estados);
    }

    //buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Estado> obtenerEstado(@PathVariable Long id){
        try{
            Estado estado = estadoService.getEstado(id);
            return ResponseEntity.ok(estado);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //crear 
    @PostMapping
    public ResponseEntity<Estado> saveEstado(@RequestBody Estado estado){
        return ResponseEntity.status(201).body(estadoService.saveRol(estado));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estadoService.deleteEstado(id);
        return ResponseEntity.noContent().build();
    }

}
