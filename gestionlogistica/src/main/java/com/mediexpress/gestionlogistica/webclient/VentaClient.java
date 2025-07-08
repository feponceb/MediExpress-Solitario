package com.mediexpress.gestionlogistica.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class VentaClient {
    private final WebClient webClient;

    public VentaClient(@Value("${ventas-service.url}") String ventasUrl) {
        this.webClient = WebClient.builder().baseUrl(ventasUrl).build();
    }

    public Map<String, Object> obtenerVentaPorId(Long idOrden) {
        return this.webClient.get()
            .uri("/venta/{id}", idOrden)
            .retrieve()
            .bodyToMono(Map.class)
            .block();
    }
}
