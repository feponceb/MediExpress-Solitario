package com.mediexpress.gestioninventario.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ProductoClient {
    private final WebClient webClient;

    public ProductoClient(@Value("${producto-service.url}") String productoServidor) {
        this.webClient = WebClient.builder().baseUrl(productoServidor).build();
    }

    public Map<String, Object> obtenerProductoId(Long id) {
        return this.webClient.get()
            .uri("/{id}", id)
            .retrieve()
            .onStatus(status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                .map(body -> new RuntimeException("Producto no encontrado")))
            .bodyToMono(Map.class).block();
    }
    public Map<String, Object> obtenerNombreYUnidadProducto(Long id) {
        Map<String, Object> producto = obtenerProductoId(id);
        if (producto == null || producto.isEmpty()) {
            throw new RuntimeException("Producto no encontrado");
        }
        return Map.of(
            "nombre", producto.get("nombre"),
            "unidad", producto.get("unidad")
        );
    }

}
