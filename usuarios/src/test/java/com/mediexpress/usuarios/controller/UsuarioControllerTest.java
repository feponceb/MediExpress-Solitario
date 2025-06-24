package com.mediexpress.usuarios.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.mediexpress.usuarios.model.Estado;
import com.mediexpress.usuarios.model.Rol;
import com.mediexpress.usuarios.model.Usuario;
import com.mediexpress.usuarios.service.UsuarioService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @MockBean
    private UsuarioService userService;

    @Autowired
    private MockMvc mockMvc;

    
        // Test GET /api/usuarios  (listar todos)
    @Test
    void getAllUsuarios_returnsOKAndJson() throws Exception {
        List<Usuario> usuarios = Arrays.asList(
            new Usuario(1L, "12345678-9", "Juan", "juan@mail.com", "pass", null, null),
            new Usuario(2L, "98765432-1", "Maria", "maria@mail.com", "pass2", null, null)
        );

        when(userService.getUsers()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].nombre").value("Juan"))
            .andExpect(jsonPath("$[1].id").value(2L))
            .andExpect(jsonPath("$[1].nombre").value("Maria"));
    }

    // Test GET /api/usuarios/{id}
    @Test
    void getUsuarioById_returnsUsuario() throws Exception {
        Usuario usuario = new Usuario(1L, "12345678-9", "Juan", "juan@mail.com", "pass", null, null);

        when(userService.getUser(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    // Test GET /api/usuarios/{id} - usuario no encontrado
    @Test
    void getUsuarioById_returnsNotFound() throws Exception {
        when(userService.getUser(1L)).thenThrow(new RuntimeException("Usuario no encontrado"));

        mockMvc.perform(get("/api/usuarios/1"))
            .andExpect(status().isNotFound());
    }

    // Test POST /api/usuarios - crear nuevo usuario
    @Test
    void createUsuario_createsAndReturns201() throws Exception {
        Usuario nuevo = new Usuario(null, "11111111-1", "Pedro", "pedro@mail.com", "pass3", null, null);
        Usuario creado = new Usuario(3L, "11111111-1", "Pedro", "pedro@mail.com", "pass3", null, null);

        when(userService.findByRut("11111111-1")).thenThrow(new RuntimeException("Usuario no encontrado con RUT: 11111111-1"));
        when(userService.saveUser(any(Usuario.class))).thenReturn(creado);

        mockMvc.perform(post("/api/usuarios")
                .contentType("application/json")
                .content("{\"rut\":\"11111111-1\",\"nombre\":\"Pedro\",\"correo\":\"pedro@mail.com\",\"password\":\"pass3\"}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(3L))
            .andExpect(jsonPath("$.nombre").value("Pedro"));
    }

    // Test POST /api/usuarios - actualizar usuario existente
    @Test
    void createUsuario_updatesExisting() throws Exception {
        Usuario existente = new Usuario(1L, "12345678-9", "Juan", "juan@mail.com", "pass", null, null);
        Usuario actualizado = new Usuario(1L, "12345678-9", "Juan Actualizado", "juan@mail.com", "pass", null, null);

        when(userService.findByRut("12345678-9")).thenReturn(existente);
        when(userService.saveUser(any(Usuario.class))).thenReturn(actualizado);

        mockMvc.perform(post("/api/usuarios")
                .contentType("application/json")
                .content("{\"rut\":\"12345678-9\",\"nombre\":\"Juan Actualizado\",\"correo\":\"juan@mail.com\",\"password\":\"pass\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Juan Actualizado"));
    }

    // Test DELETE /api/usuarios/{id}
    @Test
    void deleteUsuario_returnsNoContent() throws Exception {
        // no need to mock service for void method usually, but could do doNothing()

        mockMvc.perform(delete("/api/usuarios/1"))
            .andExpect(status().isNoContent());
    }

    // Test PUT /api/usuarios/{id}
    @Test
    void updateUsuario_returnsUpdated() throws Exception {
        Usuario actualizado = new Usuario(1L, "12345678-9", "Juan Actualizado", "juan@mail.com", "pass", null, null);

        when(userService.updateUser(eq(1L), any(Usuario.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/usuarios/1")
                .contentType("application/json")
                .content("{\"rut\":\"12345678-9\",\"nombre\":\"Juan Actualizado\",\"correo\":\"juan@mail.com\",\"password\":\"pass\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Juan Actualizado"));
    }

    // Test PUT /api/usuarios/{id} - usuario no encontrado
    @Test
    void updateUsuario_returnsNotFound() throws Exception {
        when(userService.updateUser(eq(1L), any(Usuario.class))).thenThrow(new RuntimeException("Usuario no encontrado"));

        mockMvc.perform(put("/api/usuarios/1")
                .contentType("application/json")
                .content("{\"rut\":\"12345678-9\",\"nombre\":\"Juan\",\"correo\":\"juan@mail.com\",\"password\":\"pass\"}"))
            .andExpect(status().isNotFound());
    }
}

    


