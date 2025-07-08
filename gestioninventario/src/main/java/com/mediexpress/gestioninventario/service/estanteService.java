package com.mediexpress.gestioninventario.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestioninventario.model.estante;
import com.mediexpress.gestioninventario.repository.estanteRepository;
import com.mediexpress.gestioninventario.webclient.ProductoClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class estanteService {

    @Autowired
    private estanteRepository EstanteRepository;

    @Autowired
    private ProductoClient productoClient;

    public List<estante> getAllEstantes() {
        return EstanteRepository.findAll();
    }

    public estante getEstante(Long id) {
        return EstanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estante no encontrado"));
    }

    public estante saveEstante(estante e) {
        Map<String, Object> producto = productoClient.obtenerProductoId(e.getProdId());
        if (producto == null || producto.isEmpty()) {
            throw new RuntimeException("Producto no encontrado, no se puede asignar al estante");
        }
        return EstanteRepository.save(e);
    }

    public estante updateEstante(Long id, estante nuevo) {
        estante existente = getEstante(id);
        // Validar existencia del producto antes de asignar
        Map<String, Object> producto = productoClient.obtenerProductoId(nuevo.getProdId());
        if (producto == null || producto.isEmpty()) {
            throw new RuntimeException("Producto no encontrado, no se puede asignar al estante");
        }

        existente.setNombreEstante(nuevo.getNombreEstante());
        existente.setProdId(nuevo.getProdId());
        return EstanteRepository.save(existente);
    }

    public void deleteEstante(Long id) {
        getEstante(id);
        EstanteRepository.deleteById(id);
    }

    public Map<String, Object> getNombreYUnidadDeProducto(Long prodId) {
    return productoClient.obtenerNombreYUnidadProducto(prodId);
}

}
