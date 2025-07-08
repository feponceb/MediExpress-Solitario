package com.mediexpress.gestionventas.webclient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ClienteClient {
    private final WebClient webClient;

    public ClienteClient(@Value("${cliente-service.url}") String clienteServidor){
        this.webClient = WebClient.builder().baseUrl(clienteServidor).build();
    }

    public Map<String, Object>obtenerClienteId(Long id){
        return this.webClient.get() //posible error en esta linea de code
            .uri("/{id}", id)
            .retrieve()
            .onStatus(status -> status.is4xxClientError(),
                response -> response.bodyToMono(String.class)
                .map(body -> new RuntimeException("Cliente no encontrado")))
            .bodyToMono(Map.class).block();
    }

}
