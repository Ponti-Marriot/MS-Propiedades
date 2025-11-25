// java
package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class ServiceRepository {

    private final WebClient webClient;

    public ServiceRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Service> findAll() {
        return webClient.get()
                .uri("/services")
                .retrieve()
                .bodyToFlux(Service.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Mono<Service> findById(UUID id) {
        return webClient.get()
                .uri("/services/{id}", id)
                .retrieve()
                .bodyToMono(Service.class)
                .onErrorResume(e -> Mono.empty());
    }

    public Flux<Service> findByCategory(String category) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/services")
                        .queryParam("category", category)
                        .build())
                .retrieve()
                .bodyToFlux(Service.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Flux<Service> findByName(String name) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/services")
                        .queryParam("name", name)
                        .build())
                .retrieve()
                .bodyToFlux(Service.class)
                .onErrorResume(e -> Flux.empty());
    }
}
