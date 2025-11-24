package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.RoomServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RoomServicesRepository {

    private final WebClient webClient;

    public RoomServicesRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public List<RoomServices> findAll() {
        try {
            List<RoomServices> list = webClient.get()
                    .uri("/room-services")
                    .retrieve()
                    .bodyToFlux(RoomServices.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public Optional<RoomServices> findById(UUID id) {
        try {
            RoomServices rs = webClient.get()
                    .uri("/room-services/{id}", id)
                    .retrieve()
                    .bodyToMono(RoomServices.class)
                    .block();
            return Optional.ofNullable(rs);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public List<RoomServices> findByServiceId(UUID serviceId) {
        try {
            List<RoomServices> list = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/room-services")
                            .queryParam("serviceId", serviceId)
                            .build())
                    .retrieve()
                    .bodyToFlux(RoomServices.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public List<RoomServices> findByRoomId(UUID roomId) {
        try {
            List<RoomServices> list = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/room-services")
                            .queryParam("roomId", roomId)
                            .build())
                    .retrieve()
                    .bodyToFlux(RoomServices.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }
}
