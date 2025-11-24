package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.Status;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StatusRepository {

    private final WebClient webClient;

    public StatusRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Status> findAll() {
        try {
            List<Status> list = webClient.get()
                    .uri("/statuses")
                    .retrieve()
                    .bodyToFlux(Status.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public Optional<Status> findById(UUID id) {
        try {
            Status status = webClient.get()
                    .uri("/statuses/{id}", id)
                    .retrieve()
                    .bodyToMono(Status.class)
                    .block();
            return Optional.ofNullable(status);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public List<Status> findByName(String name) {
        try {
            List<Status> list = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/statuses")
                            .queryParam("name", name)
                            .build())
                    .retrieve()
                    .bodyToFlux(Status.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }
}
