package com.mediexpress.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mediexpress.usuarios.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByRut(String rut);
    Optional<Usuario> findByCorreo(String correo);
    Usuario findByPassword(String password);
}
