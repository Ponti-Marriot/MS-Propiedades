// java
package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.HotelPropertyRoom;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class HotelPropertyRoomRepository {

    private final WebClient webClient;

    public HotelPropertyRoomRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public List<HotelPropertyRoom> findAll() {
        try {
            List<HotelPropertyRoom> list = webClient.get()
                    .uri("/hotel-property-rooms")
                    .retrieve()
                    .bodyToFlux(HotelPropertyRoom.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public Optional<HotelPropertyRoom> findById(UUID id) {
        try {
            HotelPropertyRoom room = webClient.get()
                    .uri("/hotel-property-rooms/{id}", id)
                    .retrieve()
                    .bodyToMono(HotelPropertyRoom.class)
                    .block();
            return Optional.ofNullable(room);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public List<HotelPropertyRoom> findByHotelPropertyId(UUID hotelPropertyId) {
        try {
            List<HotelPropertyRoom> list = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/hotel-property-rooms")
                            .queryParam("hotelPropertyId", hotelPropertyId)
                            .build())
                    .retrieve()
                    .bodyToFlux(HotelPropertyRoom.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public List<HotelPropertyRoom> findByRoomId(UUID roomId) {
        try {
            List<HotelPropertyRoom> list = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/hotel-property-rooms")
                            .queryParam("roomId", roomId)
                            .build())
                    .retrieve()
                    .bodyToFlux(HotelPropertyRoom.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }
}
