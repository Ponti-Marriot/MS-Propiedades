// java
package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.AvailabilityDates;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class AvailabilityDatesRepository {

    private final WebClient webClient;

    public AvailabilityDatesRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<AvailabilityDates> findAll() {
        return webClient.get()
                .uri("/availability-dates")
                .retrieve()
                .bodyToFlux(AvailabilityDates.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Mono<AvailabilityDates> findById(UUID id) {
        return webClient.get()
                .uri("/availability-dates/{id}", id)
                .retrieve()
                .bodyToMono(AvailabilityDates.class)
                .onErrorResume(e -> Mono.empty());
    }

    public Flux<AvailabilityDates> findByRoomId(UUID roomId) {
        return webClient.get()
                .uri("/availability-dates/room/{roomId}", roomId)
                .retrieve()
                .bodyToFlux(AvailabilityDates.class)
                .onErrorResume(e -> Flux.empty());
    }
}
