package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.Location;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Repository
public class LocationRepository {

    private final WebClient webClient;
    private final LocationJpaRepository jpa;

    public LocationRepository(@Qualifier("propertiesWebClient") WebClient webClient,
                              LocationJpaRepository jpa) {
        this.webClient = webClient;
        this.jpa = jpa;
    }

    /**
     * Fetches all locations from the external service and saves them into the local H2 DB.
     * Returns a Mono that completes when save finishes.
     */
    public Mono<Void> fetchAndSaveAll() {
        return webClient.get()
                .uri("/locations")
                .retrieve()
                .bodyToFlux(Location.class)
                .collectList()
                .flatMap(list -> Mono.fromCallable(() -> {
                    jpa.saveAll(list);
                    return true;
                }).subscribeOn(Schedulers.boundedElastic()))
                .then()
                .onErrorResume(e -> Mono.empty());
    }

    /**
     * Returns locations already stored in the local DB as a reactive Flux.
     */
    public Flux<Location> findAllSaved() {
        return Flux.defer(() -> Flux.fromIterable(jpa.findAll()))
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(e -> Flux.empty());
    }
}
