// java
package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.AvailabilityDates;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AvailabilityDatesRepository {

    private final WebClient webClient;

    public AvailabilityDatesRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public List<AvailabilityDates> findAll() {
        try {
            List<AvailabilityDates> list = webClient.get()
                    .uri("/availability-dates")
                    .retrieve()
                    .bodyToFlux(AvailabilityDates.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public Optional<AvailabilityDates> findById(UUID id) {
        try {
            AvailabilityDates ad = webClient.get()
                    .uri("/availability-dates/{id}", id)
                    .retrieve()
                    .bodyToMono(AvailabilityDates.class)
                    .block();
            return Optional.ofNullable(ad);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public List<AvailabilityDates> findByRoomId(UUID roomId) {
        try {
            List<AvailabilityDates> list = webClient.get()
                    .uri("/availability-dates/room/{roomId}", roomId)
                    .retrieve()
                    .bodyToFlux(AvailabilityDates.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }
}
