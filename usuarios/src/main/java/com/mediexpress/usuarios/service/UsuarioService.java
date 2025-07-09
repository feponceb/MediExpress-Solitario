package com.mediexpress.usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mediexpress.usuarios.model.Rol;
import com.mediexpress.usuarios.model.Usuario;
import com.mediexpress.usuarios.repository.RolRepository;
import com.mediexpress.usuarios.repository.UsuarioRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository UserRepository;
    @Autowired
    private RolRepository RolRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //traer todos los users
    public List<Usuario> getUsers(){
        return UserRepository.findAll();
    }

    //buscar usuarios por id
    public Usuario getUser(Long id){
        return UserRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
    }

    //crear usuarios
    public Usuario saveUser(Usuario User){
        if (User.getRol() != null && User.getRol().getIdRol() != null) {
            Rol rol = RolRepository.findById(User.getRol().getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            User.setRol(rol);
        } else{
            User.setRol(null);
        }
        if (User.getPassword() != null) {
            User.setPassword(passwordEncoder.encode(User.getPassword()));
        }

        return UserRepository.save(User);
    }

    //deletear
    public void deleteUser(Long id){
        UserRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        UserRepository.deleteById(id);
    }

    //buscar por rut
    public Usuario findByRut(String rut) {
        Usuario usuario = UserRepository.findByRut(rut);
    if (usuario == null) {
        return null; 
    }
    return usuario;
    }

    //Update de usuario
    public Usuario updateUser(Long id, Usuario nuevoUsuario) {
    Usuario existente = UserRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    // Verificador de RUT en uso
    Usuario usuarioConEseRut = UserRepository.findByRut(nuevoUsuario.getRut());
    if (usuarioConEseRut != null && !usuarioConEseRut.getId().equals(id)) {
        throw new RuntimeException("El RUT ya está en uso por otro usuario");
    }

    // Actualizar campos permitidos
    existente.setRut(nuevoUsuario.getRut());
    existente.setNombre(nuevoUsuario.getNombre());
    existente.setCorreo(nuevoUsuario.getCorreo());
    existente.setPassword(nuevoUsuario.getPassword());
    
    existente.setRol(nuevoUsuario.getRol());
    
    // Guardar cambios
    return UserRepository.save(existente);
    }

    //buscar por correo
    public Usuario findByCorreo(String correo) {
        return UserRepository.findByCorreo(correo)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el correo: " + correo));
    }

    //buscar password de usuario
    public Usuario findByPassword(String password) {
        Usuario usuario = UserRepository.findByPassword(password);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con la contraseña proporcionada");
        }
        return usuario;
    }

}
