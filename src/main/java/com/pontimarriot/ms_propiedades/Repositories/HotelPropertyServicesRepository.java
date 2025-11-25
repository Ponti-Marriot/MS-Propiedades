// java
package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.HotelPropertyServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class HotelPropertyServicesRepository {

    private final WebClient webClient;

    public HotelPropertyServicesRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<HotelPropertyServices> findAll() {
        return webClient.get()
                .uri("/hotel-property-services")
                .retrieve()
                .bodyToFlux(HotelPropertyServices.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Mono<HotelPropertyServices> findById(UUID id) {
        return webClient.get()
                .uri("/hotel-property-services/{id}", id)
                .retrieve()
                .bodyToMono(HotelPropertyServices.class)
                .onErrorResume(e -> Mono.empty());
    }

    public Flux<HotelPropertyServices> findByServiceId(UUID serviceId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/hotel-property-services")
                        .queryParam("service_id", serviceId)
                        .build())
                .retrieve()
                .bodyToFlux(HotelPropertyServices.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Flux<HotelPropertyServices> findByHotelPropertyId(UUID hotelPropertyId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/hotel-property-services")
                        .queryParam("hotel_property_id", hotelPropertyId)
                        .build())
                .retrieve()
                .bodyToFlux(HotelPropertyServices.class)
                .onErrorResume(e -> Flux.empty());
    }
}
