package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.HotelProperty;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class HotelPropertyRepository {

    private final WebClient webClient;

    public HotelPropertyRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public List<HotelProperty> findAll() {
        try {
            List<HotelProperty> list = webClient.get()
                    .uri("/properties")
                    .retrieve()
                    .bodyToFlux(HotelProperty.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public Optional<HotelProperty> findById(UUID id) {
        try {
            HotelProperty hp = webClient.get()
                    .uri("/properties/{id}", id)
                    .retrieve()
                    .bodyToMono(HotelProperty.class)
                    .block();
            return Optional.ofNullable(hp);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public List<HotelProperty> findByLocationId(UUID locationId) {
        try {
            List<HotelProperty> list = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/properties").queryParam("locationId", locationId).build())
                    .retrieve()
                    .bodyToFlux(HotelProperty.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public List<HotelProperty> findByAvailabilityDatesId(UUID availabilityDatesId) {
        try {
            List<HotelProperty> list = webClient.get()
                    .uri("/properties/availability/{availabilityDatesId}", availabilityDatesId)
                    .retrieve()
                    .bodyToFlux(HotelProperty.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }
}
