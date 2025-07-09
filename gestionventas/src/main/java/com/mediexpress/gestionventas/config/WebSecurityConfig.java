package com.mediexpress.gestionventas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.mediexpress.gestionventas.service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(customUserDetailsService)
        .passwordEncoder(passwordEncoder())
        .and()
        .build();
}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
        
                //VENTAS
                .requestMatchers("/ventas/**").hasAnyRole("LOGISTICA", "ADMIN")
                .requestMatchers("/detalle/**").hasAnyRole("LOGISTICA", "ADMIN")

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
