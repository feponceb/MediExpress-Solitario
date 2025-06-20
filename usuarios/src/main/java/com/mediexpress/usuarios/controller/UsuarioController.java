package com.mediexpress.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediexpress.usuarios.model.Usuario;
import com.mediexpress.usuarios.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    public UsuarioService UserService; 

    //agarra todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsers(){
        List<Usuario> users = UserService.getUsers();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    //buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUser(@PathVariable Long id){
        try{
            Usuario User = UserService.getUser(id);
            return ResponseEntity.ok(User);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //crear usuarios
    @PostMapping
    public ResponseEntity<Usuario> saveUser(@RequestBody Usuario user){
        Usuario usuarioExistente = UserService.findByRut(user.getRut());
    if (usuarioExistente != null) {
        // Actualizar campos
        usuarioExistente.setNombre(user.getNombre());
        usuarioExistente.setCorreo(user.getCorreo());
        usuarioExistente.setPassword(user.getPassword());
        usuarioExistente.setEstado(user.getEstado());
        usuarioExistente.setRol(user.getRol());
        
        Usuario actualizado = UserService.saveUser(usuarioExistente);
        return ResponseEntity.ok(actualizado);
    } else {
        // Crear nuevo usuario
        Usuario nuevoUsuario = UserService.saveUser(user);
        return ResponseEntity.status(201).body(nuevoUsuario);
        }
    }
    

    //deleteo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        UserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //Updateo
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody Usuario usuarioCambio){
        try{
            Usuario user = UserService.updateUser(id, usuarioCambio);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    

}
