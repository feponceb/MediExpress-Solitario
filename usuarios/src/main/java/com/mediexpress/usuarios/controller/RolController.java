package com.mediexpress.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediexpress.usuarios.model.Rol;
import com.mediexpress.usuarios.service.RolService;

@RestController
@RequestMapping("/api/rol")
public class RolController {
    @Autowired
    public RolService rolService;

    //agarrar todo
    @GetMapping
    public ResponseEntity<List<Rol>> obtenerRoles(){
        List<Rol> roles = rolService.getRoles();
        if(roles.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    //buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerRol(@PathVariable Long id){
        try{
            Rol rol = rolService.getRol(id);
            return ResponseEntity.ok(rol);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //crear 
    @PostMapping
    public ResponseEntity<Rol> saveRol(@RequestBody Rol rol){
        return ResponseEntity.status(201).body(rolService.saveRol(rol));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rolService.deleteRol(id);
        return ResponseEntity.noContent().build();
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Rol> updateRol(@PathVariable Long id, @RequestBody Rol rol){
        try{
            Rol rolUpdate = rolService.updateRol(id, rol);
            return ResponseEntity.ok(rolUpdate);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

}
