package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.Status;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class StatusRepository {

    private final WebClient webClient;

    public StatusRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Status> findAll() {
        return webClient.get()
                .uri("/statuses")
                .retrieve()
                .bodyToFlux(Status.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Mono<Status> findById(UUID id) {
        return webClient.get()
                .uri("/statuses/{id}", id)
                .retrieve()
                .bodyToMono(Status.class)
                .onErrorResume(e -> Mono.empty());
    }

    public Flux<Status> findByName(String name) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/statuses")
                        .queryParam("name", name)
                        .build())
                .retrieve()
                .bodyToFlux(Status.class)
                .onErrorResume(e -> Flux.empty());
    }
}
