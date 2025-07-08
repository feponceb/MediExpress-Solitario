package com.mediexpress.gestionreclamos.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionreclamos.model.incidencia;
import com.mediexpress.gestionreclamos.repository.incidenciaRepository;
import com.mediexpress.gestionreclamos.webclient.ClienteClient;
import com.mediexpress.gestionreclamos.webclient.VentaClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class incidenciaService {

    @Autowired
    private incidenciaRepository incidenciaRepository;

    @Autowired
    private VentaClient ventaClient;    

    @Autowired
    private ClienteClient clienteClient;

    public List<incidencia> getIncidencias() {
        return incidenciaRepository.findAll();
    }

    public incidencia getIncidencia(Long id) {
        return incidenciaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
    }

    public incidencia saveIncidencia(incidencia i) {
        // Validar existencia de la venta
        Map<String, Object> venta = ventaClient.obtenerVentaPorId(i.getIdVenta());
        if (venta == null || venta.isEmpty()) {
            throw new RuntimeException("Venta no encontrada, no se puede crear incidencia");
        }
        // Validar existencia del cliente (si aplica)
        Map<String, Object> cliente = clienteClient.obtenerClienteId(i.getIdCliente());
        if (cliente == null || cliente.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado, no se puede crear incidencia");
        }
        return incidenciaRepository.save(i);
    }

    public incidencia updateIncidencia(Long id, incidencia nuevo) {
        incidencia existente = getIncidencia(id);

        // Validar existencia de la venta
        Map<String, Object> venta = ventaClient.obtenerVentaPorId(nuevo.getIdVenta());
        if (venta == null || venta.isEmpty()) {
            throw new RuntimeException("Venta no encontrada, no se puede actualizar incidencia");
        }
        // Validar existencia del cliente (si aplica)
        Map<String, Object> cliente = clienteClient.obtenerClienteId(nuevo.getIdCliente());
        if (cliente == null || cliente.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado, no se puede actualizar incidencia");
        }

        existente.setComentario(nuevo.getComentario());
        existente.setMotivo(nuevo.getMotivo());
        existente.setIdVenta(nuevo.getIdVenta());
        existente.setIdCliente(nuevo.getIdCliente());
        return incidenciaRepository.save(existente);
    }

    public void deleteIncidencia(Long id) {
        getIncidencia(id);
        incidenciaRepository.deleteById(id);
    }

}
