package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.Room;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RoomRepository {

    private final WebClient webClient;

    public RoomRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Room> findAll() {
        try {
            List<Room> list = webClient.get()
                    .uri("/rooms")
                    .retrieve()
                    .bodyToFlux(Room.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public Optional<Room> findById(UUID id) {
        try {
            Room room = webClient.get()
                    .uri("/rooms/{id}", id)
                    .retrieve()
                    .bodyToMono(Room.class)
                    .block();
            return Optional.ofNullable(room);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public List<Room> findByAvailabilityDatesId(UUID availabilityDatesId) {
        try {
            List<Room> list = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/rooms")
                            .queryParam("availabilityDatesId", availabilityDatesId)
                            .build())
                    .retrieve()
                    .bodyToFlux(Room.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }
}
