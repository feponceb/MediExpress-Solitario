package com.mediexpress.gestionventas.service;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionventas.webclient.ClienteClient;
import com.mediexpress.gestionventas.webclient.UsuarioDTO;
@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final ClienteClient clienteClient;

    public CustomUserDetailsService(ClienteClient clienteClient) {
        this.clienteClient = clienteClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioDTO usuario = clienteClient.obtenerPorCorreo(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el correo: " + username);
        }

        String rolSpring = "ROLE_" + usuario.getRol().toUpperCase();

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getPassword())
                .authorities(rolSpring)
                .build();
    }
    
}
