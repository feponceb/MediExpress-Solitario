package com.mediexpress.gestionventas.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionventas.model.venta;
import com.mediexpress.gestionventas.repository.ventaRepository;
import com.mediexpress.gestionventas.webclient.ClienteClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ventaService {

    @Autowired
    private ventaRepository VentaRepository;

    @Autowired
    private ClienteClient clienteClient;

    public List<venta> getVentas() {
        return VentaRepository.findAll();
    }

    public venta getVenta(Long id) {
        return VentaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    public venta saveVenta(venta v) {
        Map<String,Object> cliente = clienteClient.obtenerClienteId(v.getIdUsuario());
        if (cliente == null || cliente.isEmpty()){
            throw new RuntimeException("Usuario no encontrado, incapaz de crear venta");
        }
        return VentaRepository.save(v);
    }

    public venta updateVenta(Long id, venta nuevo) {
        // Validar existencia del usuario antes de actualizar
        Map<String, Object> cliente = clienteClient.obtenerClienteId(nuevo.getIdUsuario());
        if (cliente == null || cliente.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado, incapaz de actualizar venta");
        }

        venta existente = getVenta(id);
        existente.setFechaVenta(nuevo.getFechaVenta());
        existente.setTotal(nuevo.getTotal());
        existente.setIdUsuario(nuevo.getIdUsuario());
        return VentaRepository.save(existente);
    }

    public void deleteVenta(Long id) {
        getVenta(id);
        VentaRepository.deleteById(id);
    }

}
