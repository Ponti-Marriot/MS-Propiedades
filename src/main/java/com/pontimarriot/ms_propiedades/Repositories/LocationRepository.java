package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.Location;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class LocationRepository {

    private final WebClient webClient;

    public LocationRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Location> findAll() {
        return webClient.get()
                .uri("/locations")
                .retrieve()
                .bodyToFlux(Location.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Mono<Location> findById(UUID id) {
        return webClient.get()
                .uri("/locations/{id}", id)
                .retrieve()
                .bodyToMono(Location.class)
                .onErrorResume(e -> Mono.empty());
    }
}
