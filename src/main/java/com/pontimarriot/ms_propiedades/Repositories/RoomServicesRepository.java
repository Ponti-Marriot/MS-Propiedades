package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.RoomServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class RoomServicesRepository {

    private final WebClient webClient;

    public RoomServicesRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<RoomServices> findAll() {
        return webClient.get()
                .uri("/room-services")
                .retrieve()
                .bodyToFlux(RoomServices.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Mono<RoomServices> findById(UUID id) {
        return webClient.get()
                .uri("/room-services/{id}", id)
                .retrieve()
                .bodyToMono(RoomServices.class)
                .onErrorResume(e -> Mono.empty());
    }

    public Flux<RoomServices> findByServiceId(UUID serviceId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/room-services")
                        .queryParam("serviceId", serviceId)
                        .build())
                .retrieve()
                .bodyToFlux(RoomServices.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Flux<RoomServices> findByRoomId(UUID roomId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/room-services")
                        .queryParam("roomId", roomId)
                        .build())
                .retrieve()
                .bodyToFlux(RoomServices.class)
                .onErrorResume(e -> Flux.empty());
    }
}
