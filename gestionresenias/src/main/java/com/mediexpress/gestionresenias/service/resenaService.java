package com.mediexpress.gestionresenias.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediexpress.gestionresenias.model.resena;
import com.mediexpress.gestionresenias.repository.resenaRepository;
import com.mediexpress.gestionresenias.webclient.ClienteClient;
import com.mediexpress.gestionresenias.webclient.ProductoClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class resenaService {

    @Autowired
    private resenaRepository ResenaRepository;

    @Autowired
    private ClienteClient ClienteClient;    

    @Autowired
    private ProductoClient ProductoClient;

    public List<resena> getResenas() {
        return ResenaRepository.findAll();
    }

    public resena getResena(Long id) {
        return ResenaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
    }

    public List<resena> getResenasByProducto(Long idProducto) {
        return ResenaRepository.findByIdProducto(idProducto);
    }

    public List<resena> getResenasByUsuario(Long idUsuario) {
        return ResenaRepository.findByIdUsuario(idUsuario);
    }

    public resena saveResena(resena r) {
        // Validar existencia del cliente
        Map<String, Object> cliente = ClienteClient.obtenerClienteId(r.getIdUsuario());
        if (cliente == null || cliente.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado, no se puede crear reseña");
        }
        // Validar existencia del producto
        Map<String, Object> producto = ProductoClient.obtenerProductoId(r.getIdProducto());
        if (producto == null || producto.isEmpty()) {
            throw new RuntimeException("Producto no encontrado, no se puede crear reseña");
        }
        return ResenaRepository.save(r);

    }

    public resena updateResena(Long id, resena nuevo) {
        // Validar existencia del cliente
        Map<String, Object> cliente = ClienteClient.obtenerClienteId(nuevo.getIdUsuario());
        if (cliente == null || cliente.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado, no se puede actualizar reseña");
        }
        // Validar existencia del producto
        Map<String, Object> producto = ProductoClient.obtenerProductoId(nuevo.getIdProducto());
        if (producto == null || producto.isEmpty()) {
            throw new RuntimeException("Producto no encontrado, no se puede actualizar reseña");
        }

        resena existente = getResena(id);
        existente.setComentario(nuevo.getComentario());
        existente.setCalificacion(nuevo.getCalificacion());
        existente.setIdUsuario(nuevo.getIdUsuario());
        existente.setIdProducto(nuevo.getIdProducto());
        existente.setFecha(nuevo.getFecha());
        return ResenaRepository.save(existente);
    }

    public void deleteResena(Long id) {
        getResena(id); 
        ResenaRepository.deleteById(id);
    }

}
