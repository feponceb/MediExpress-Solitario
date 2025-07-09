package com.mediexpress.usuarios.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mediexpress.usuarios.model.Rol;
import com.mediexpress.usuarios.model.Usuario;
import com.mediexpress.usuarios.repository.RolRepository;
import com.mediexpress.usuarios.repository.UsuarioRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initDatabase(
            RolRepository rolRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            if (rolRepository.count() == 0) {
                rolRepository.save(new Rol(null, "ADMIN"));
                rolRepository.save(new Rol(null, "CLIENTE"));
                rolRepository.save(new Rol(null, "LOGISTICA"));
                rolRepository.save(new Rol(null, "GESTOR_PRODUCTOS"));
                rolRepository.save(new Rol(null, "GESTOR_USUARIOS"));
                rolRepository.save(new Rol(null, "GESTOR_INVENTARIO"));
                rolRepository.save(new Rol(null, "GESTOR_RECLAMOS"));
                rolRepository.save(new Rol(null, "GESTOR_RESENAS"));
                rolRepository.save(new Rol(null, "GESTOR_VENTAS"));
            }

            if (usuarioRepository.count() == 0) {
                Rol adminRol = rolRepository.findByNombreRol("ADMIN").orElseThrow();
                Rol clienteRol = rolRepository.findByNombreRol("CLIENTE").orElseThrow();
                Rol gestorUsuariosRol = rolRepository.findByNombreRol("GESTOR_USUARIOS").orElseThrow();
                Rol logisticaRol = rolRepository.findByNombreRol("LOGISTICA").orElseThrow();
                Rol gestorProductosRol = rolRepository.findByNombreRol("GESTOR_PRODUCTOS").orElseThrow();
                Rol gestorInventarioRol = rolRepository.findByNombreRol("GESTOR_INVENTARIO").orElseThrow();
                Rol gestorReclamosRol = rolRepository.findByNombreRol("GESTOR_RECLAMOS").orElseThrow();
                Rol gestorResenasRol = rolRepository.findByNombreRol("GESTOR_RESENAS").orElseThrow();
                Rol gestorVentasRol = rolRepository.findByNombreRol("GESTOR_VENTAS").orElseThrow();

                usuarioRepository.save(new Usuario(null,"11111111-1", "Admin", "admin@admin.com", passwordEncoder.encode("admin123"), adminRol));
                usuarioRepository.save(new Usuario(null,"22222222-2", "Jose", "jose@gmail.com", passwordEncoder.encode("jose123"), clienteRol));
                usuarioRepository.save(new Usuario(null,"33333333-3", "Maria", "maria@gmail.com", passwordEncoder.encode("maria123"), gestorUsuariosRol));
                usuarioRepository.save(new Usuario(null, "44444444-4", "Pedro Logística", "pedro@gmail.com", passwordEncoder.encode("pedro123"), logisticaRol));
                usuarioRepository.save(new Usuario(null, "55555555-5", "Ana Productos", "ana@gmail.com", passwordEncoder.encode("ana123"), gestorProductosRol));
                usuarioRepository.save(new Usuario(null, "66666666-6", "Luis Inventario", "luis@gmail.com", passwordEncoder.encode("luis123"), gestorInventarioRol));
                usuarioRepository.save(new Usuario(null, "77777777-7", "Carla Reclamos", "carla@gmail.com", passwordEncoder.encode("carla123"), gestorReclamosRol));
                usuarioRepository.save(new Usuario(null, "88888888-8", "Diego Reseñas", "diego@gmail.com", passwordEncoder.encode("diego123"), gestorResenasRol));
                usuarioRepository.save(new Usuario(null, "99999999-9", "Sofia Ventas", "sofia@gmail.com", passwordEncoder.encode("sofia123"), gestorVentasRol));


            }
        };
    }

}
