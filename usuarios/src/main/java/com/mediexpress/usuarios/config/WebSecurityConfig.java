package com.mediexpress.usuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            
                //LIBRE
                .requestMatchers("/api/usuarios/correo/**").permitAll()
     
               //INVENTARIO
                .requestMatchers("/estantes/**").hasAnyRole("GESTOR_INVENTARIO", "ADMIN")
                .requestMatchers("/filas/**").hasAnyRole("GESTOR_INVENTARIO", "ADMIN")
     
                //LOGISTICA
                .requestMatchers("/encargos/**").hasAnyRole("LOGISTICA", "ADMIN")
                .requestMatchers("/estados/**").hasAnyRole("LOGISTICA", "ADMIN")
     
                //PRODUCTOS
                .requestMatchers("/api/productos/**").hasAnyRole("GESTOR_PRODUCTOS", "ADMIN")
                .requestMatchers("/api/categoria/**").hasAnyRole("GESTOR_PRODUCTOS", "ADMIN")
      
                //RECLAMOS
                .requestMatchers("/incidencia/**").hasAnyRole("GESTOR_RECLAMOS", "ADMIN")
                .requestMatchers("/motivos/**").hasAnyRole("GESTOR_RECLAMOS", "ADMIN")
       
                //RESEÑAS
                .requestMatchers("/resena/**").hasAnyRole("GESTOR_RESENAS", "ADMIN")
        
                //VENTAS
                .requestMatchers("/ventas/**").hasAnyRole("LOGISTICA", "ADMIN")
                .requestMatchers("/detalle/**").hasAnyRole("LOGISTICA", "ADMIN")
        
                //USUARIOS
                .requestMatchers("/api/usuarios/**").hasAnyRole("ADMIN","GESTOR_USUARIOS")
                .requestMatchers("/api/rol/**").hasAnyRole("ADMIN","GESTOR_USUARIOS")

                //CLIENTE
                .requestMatchers(HttpMethod.GET,"/api/productos/**").hasAnyRole("CLIENTE")
                .requestMatchers(HttpMethod.GET,"/motivos/**").hasAnyRole("CLIENTE")
                .requestMatchers(HttpMethod.POST,"/resena/**").hasAnyRole("CLIENTE")
                .requestMatchers(HttpMethod.POST,"/incidencia/**").hasAnyRole("CLIENTE")

                
                
                                
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
