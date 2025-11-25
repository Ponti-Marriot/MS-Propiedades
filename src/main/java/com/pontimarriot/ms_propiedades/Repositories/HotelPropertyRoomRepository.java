// java
package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.DTOs.HotelPropertyRoomDTO;
import com.pontimarriot.ms_propiedades.Entities.HotelPropertyRoom;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class HotelPropertyRoomRepository {

    private final WebClient webClient;

    public HotelPropertyRoomRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<HotelPropertyRoom> findAll() {
        return webClient.get()
                .uri("/hotel-property-rooms")
                .retrieve()
                .bodyToFlux(HotelPropertyRoom.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Mono<HotelPropertyRoom> findById(UUID id) {
        return webClient.get()
                .uri("/hotel-property-rooms/{id}", id)
                .retrieve()
                .bodyToMono(HotelPropertyRoom.class)
                .onErrorResume(e -> Mono.empty());
    }

    public Flux<HotelPropertyRoom> findByHotelPropertyId(UUID hotelPropertyId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/hotel-property-rooms")
                        .queryParam("hotel_property_id", hotelPropertyId)
                        .build())
                .retrieve()
                .bodyToFlux(HotelPropertyRoom.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Flux<HotelPropertyRoom> findByRoomId(UUID roomId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/hotel-property-rooms")
                        .queryParam("room_id", roomId)
                        .build())
                .retrieve()
                .bodyToFlux(HotelPropertyRoom.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Mono<HotelPropertyRoom> save(HotelPropertyRoom hotelPropertyRoom) {
        return webClient.post()
                .uri("/hotel-property-rooms")
                .bodyValue(hotelPropertyRoom)
                .retrieve()
                .bodyToMono(HotelPropertyRoom.class)
                .onErrorResume(e -> Mono.empty());
    }
}
