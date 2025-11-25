package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.Room;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class RoomRepository {

    private final WebClient webClient;

    public RoomRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Room> findAll() {
        return webClient.get()
                .uri("/rooms")
                .retrieve()
                .bodyToFlux(Room.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Mono<Room> findById(UUID id) {
        return webClient.get()
                .uri("/rooms/{id}", id)
                .retrieve()
                .bodyToMono(Room.class)
                .onErrorResume(e -> Mono.empty());
    }

    public Flux<Room> findByAvailabilityDatesId(UUID availabilityDatesId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/rooms")
                        .queryParam("availabilityDatesId", availabilityDatesId)
                        .build())
                .retrieve()
                .bodyToFlux(Room.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Mono<Room> save(Room room) {
        return webClient.post()
                .uri("/rooms")
                .bodyValue(room)
                .retrieve()
                .bodyToMono(Room.class)
                .onErrorResume(e -> Mono.empty());
    }
}
