// java
package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ServiceRepository {

    private final WebClient webClient;

    public ServiceRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Service> findAll() {
        try {
            List<Service> list = webClient.get()
                    .uri("/services")
                    .retrieve()
                    .bodyToFlux(Service.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public Optional<Service> findById(UUID id) {
        try {
            Service svc = webClient.get()
                    .uri("/services/{id}", id)
                    .retrieve()
                    .bodyToMono(Service.class)
                    .block();
            return Optional.ofNullable(svc);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public List<Service> findByCategory(String category) {
        try {
            List<Service> list = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/services")
                            .queryParam("category", category)
                            .build())
                    .retrieve()
                    .bodyToFlux(Service.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public List<Service> findByName(String name) {
        try {
            List<Service> list = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/services")
                            .queryParam("name", name)
                            .build())
                    .retrieve()
                    .bodyToFlux(Service.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }
}
