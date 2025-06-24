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

import com.mediexpress.usuarios.model.Rol;
import com.mediexpress.usuarios.repository.RolRepository;

@ExtendWith(MockitoExtension.class)
public class RolServiceTest {

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RolService rolService;

    @Test
    void saveRol_returnsSavedRol() {
        Rol nuevo = new Rol(1L, "ADMIN");

        when(rolRepository.save(nuevo)).thenReturn(nuevo);

        Rol resultado = rolService.saveRol(nuevo);

        assertEquals(nuevo, resultado);
    }

    @Test
    void getRoles_returnsList() {
        List<Rol> lista = List.of(new Rol(1L, "USER"));
        when(rolRepository.findAll()).thenReturn(lista);

        List<Rol> resultado = rolService.getRoles();

        assertEquals(1, resultado.size());
        assertEquals("USER", resultado.get(0).getNombreRol());
    }

    @Test
    void getRol_validId_returnsRol() {
        Rol r = new Rol(1L, "INVITADO");
        when(rolRepository.findById(1L)).thenReturn(Optional.of(r));

        Rol resultado = rolService.getRol(1L);

        assertEquals("INVITADO", resultado.getNombreRol());
    }

    @Test
    void getRol_invalidId_throwsException() {
        when(rolRepository.findById(99L)).thenReturn(Optional.empty());

        Exception e = assertThrows(RuntimeException.class, () -> rolService.getRol(99L));
        assertEquals("Rol no encontrado", e.getMessage());
    }

    @Test
    void deleteRol_existingId_deletesRol() {
        rolService.deleteRol(1L);
        verify(rolRepository).deleteById(1L);
    }

    @Test
    void updateRol_validData_updatesSuccessfully() {
        Rol original = new Rol(1L, "BASICO");
        Rol actualizado = new Rol(1L, "AVANZADO");

        when(rolRepository.findById(1L)).thenReturn(Optional.of(original));
        when(rolRepository.save(original)).thenReturn(actualizado);

        Rol resultado = rolService.updateRol(1L, actualizado);

        assertEquals("AVANZADO", resultado.getNombreRol());
    }
}
