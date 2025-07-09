package com.mediexpress.usuarios.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mediexpress.usuarios.model.Usuario;
import com.mediexpress.usuarios.repository.UsuarioRepository;

@Service
public class CustomUserDetailsSerivce implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + username));
            
        String rolSpring = "ROLE_" + usuario.getRol().getNombreRol().toUpperCase();

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getPassword()) // ya debe estar encriptada en BD
                .authorities(Collections.singletonList(() -> rolSpring))
                .build();
    }

}
