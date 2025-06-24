package com.mediexpress.gestionventas.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import com.mediexpress.gestionventas.model.detalle;
import com.mediexpress.gestionventas.repository.detalleRepository;

@ExtendWith(MockitoExtension.class)
public class detalleServiceTest {

    @Mock
    private detalleRepository detalleRepository;

    @InjectMocks
    private detalleService detalleService;

    @Test
    void getDetalles_returnsList() {
        List<detalle> detalles = Arrays.asList(
            new detalle(1L, 5, 100, 500, null, 10L),
            new detalle(2L, 3, 200, 600, null, 20L)
        );

        when(detalleRepository.findAll()).thenReturn(detalles);

        List<detalle> result = detalleService.getDetalles();

        assertThat(result).isEqualTo(detalles);
    }

    @Test
    void getDetalle_returnsDetalle() {
        detalle d = new detalle(1L, 5, 100, 500, null, 10L);

        when(detalleRepository.findById(1L)).thenReturn(java.util.Optional.of(d));

        detalle result = detalleService.getDetalle(1L);

        assertThat(result).isEqualTo(d);
    }

    @Test
    void saveDetalle_returnsSavedDetalle() {
        detalle d = new detalle(null, 5, 100, 500, null, 10L);

        when(detalleRepository.save(d)).thenReturn(d);

        detalle result = detalleService.saveDetalle(d);

        assertThat(result).isSameAs(d);
    }
}
