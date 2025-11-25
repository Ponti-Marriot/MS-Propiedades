package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.HotelProperty;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class HotelPropertyRepository {

    private final WebClient webClient;
    private final String baseUrl = "/properties";

    public HotelPropertyRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<HotelProperty> findAll() {
        return webClient.get()
                .uri(baseUrl+"/propertyList")
                .retrieve()
                .bodyToFlux(HotelProperty.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Mono<HotelProperty> findById(UUID id) {
        return webClient.get()
                .uri(baseUrl + "/properties/{id}", id)
                .retrieve()
                .bodyToMono(HotelProperty.class)
                .onErrorResume(e -> Mono.empty());
    }

    public Flux<HotelProperty> findByLocationId(UUID locationId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(baseUrl+"/properties").queryParam("locationId", locationId).build())
                .retrieve()
                .bodyToFlux(HotelProperty.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Flux<HotelProperty> findByAvailabilityDatesId(UUID availabilityDatesId) {
        return webClient.get()
                .uri(baseUrl+"/properties/availability/{availabilityDatesId}", availabilityDatesId)
                .retrieve()
                .bodyToFlux(HotelProperty.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Mono<HotelProperty> save(HotelProperty hotelProperty) {
        return webClient.post()
                .uri(baseUrl)
                .bodyValue(hotelProperty)
                .retrieve()
                .bodyToMono(HotelProperty.class)
                .onErrorResume(e -> Mono.empty());
    }
}
