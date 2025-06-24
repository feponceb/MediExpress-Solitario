package com.mediexpress.usuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mediexpress.usuarios.model.Estado;
import com.mediexpress.usuarios.model.Rol;
import com.mediexpress.usuarios.model.Usuario;
import com.mediexpress.usuarios.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository userRepository;

    @InjectMocks
    private UsuarioService userService;

    @Test
    void saveUser_returnsSavedUsuario() {
        Usuario nuevo = new Usuario(1L, "11.111.111-1", "Felipe", "felipe@mail.com", "1234", new Estado(), new Rol());

        when(userRepository.save(nuevo)).thenReturn(nuevo);

        Usuario resultado = userService.saveUser(nuevo);

        assertEquals(nuevo, resultado);
    }

    @Test
    void getUsers_returnsList() {
        List<Usuario> lista = List.of(new Usuario(1L, "22.222.222-2", "Ana", "ana@mail.com", "pwd", new Estado(), new Rol()));
        when(userRepository.findAll()).thenReturn(lista);

        List<Usuario> resultado = userService.getUsers();

        assertEquals(1, resultado.size());
        assertEquals("Ana", resultado.get(0).getNombre());
    }

    @Test
    void getUser_validId_returnsUsuario() {
        Usuario u = new Usuario(1L, "33.333.333-3", "Luis", "luis@mail.com", "pw", new Estado(), new Rol());
        when(userRepository.findById(1L)).thenReturn(Optional.of(u));

        Usuario resultado = userService.getUser(1L);

        assertEquals("Luis", resultado.getNombre());
    }

    @Test
    void getUser_invalidId_throwsException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        Exception e = assertThrows(RuntimeException.class, () -> userService.getUser(99L));
        assertEquals("Usuario no encontrado", e.getMessage());
    }

    @Test
    void deleteUser_existingId_deletesUsuario() {
        Usuario u = new Usuario(1L, "44.444.444-4", "Carlos", "carlos@mail.com", "abc", new Estado(), new Rol());
        when(userRepository.findById(1L)).thenReturn(Optional.of(u));

        userService.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    void findByRut_existingRut_returnsUsuario() {
        Usuario u = new Usuario(1L, "55.555.555-5", "Lucía", "lucia@mail.com", "clave", new Estado(), new Rol());
        when(userRepository.findByRut("55.555.555-5")).thenReturn(u);

        Usuario resultado = userService.findByRut("55.555.555-5");

        assertEquals("Lucía", resultado.getNombre());
    }

    @Test
    void updateUser_validData_updatesSuccessfully() {
        Usuario original = new Usuario(1L, "66.666.666-6", "Pedro", "pedro@mail.com", "abc", new Estado(), new Rol());
        Usuario actualizado = new Usuario(1L, "66.666.666-6", "Pedro M", "pedrom@mail.com", "nuevo", new Estado(), new Rol());

        when(userRepository.findById(1L)).thenReturn(Optional.of(original));
        when(userRepository.findByRut("66.666.666-6")).thenReturn(original);
        when(userRepository.save(original)).thenReturn(actualizado);

        Usuario resultado = userService.updateUser(1L, actualizado);

        assertEquals("Pedro M", resultado.getNombre());
        assertEquals("pedrom@mail.com", resultado.getCorreo());
    }

    @Test
    void updateUser_rutDuplicado_throwsException() {
        Usuario existente = new Usuario(1L, "77.777.777-7", "Ana", "ana@mail.com", "pw", new Estado(), new Rol());
        Usuario duplicado = new Usuario(2L, "77.777.777-7", "Otro", "otro@mail.com", "123", new Estado(), new Rol());

        when(userRepository.findById(2L)).thenReturn(Optional.of(duplicado));
        when(userRepository.findByRut("77.777.777-7")).thenReturn(existente);

        Exception e = assertThrows(RuntimeException.class, () -> userService.updateUser(2L, duplicado));
        assertEquals("El RUT ya está en uso por otro usuario", e.getMessage());
    }

}
