// java
package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.HotelPropertyServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class HotelPropertyServicesRepository {

    private final WebClient webClient;

    public HotelPropertyServicesRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public List<HotelPropertyServices> findAll() {
        try {
            List<HotelPropertyServices> list = webClient.get()
                    .uri("/hotel-property-services")
                    .retrieve()
                    .bodyToFlux(HotelPropertyServices.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public Optional<HotelPropertyServices> findById(UUID id) {
        try {
            HotelPropertyServices svc = webClient.get()
                    .uri("/hotel-property-services/{id}", id)
                    .retrieve()
                    .bodyToMono(HotelPropertyServices.class)
                    .block();
            return Optional.ofNullable(svc);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public List<HotelPropertyServices> findByServiceId(UUID serviceId) {
        try {
            List<HotelPropertyServices> list = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/hotel-property-services")
                            .queryParam("serviceId", serviceId)
                            .build())
                    .retrieve()
                    .bodyToFlux(HotelPropertyServices.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public List<HotelPropertyServices> findByHotelPropertyId(UUID hotelPropertyId) {
        try {
            List<HotelPropertyServices> list = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/hotel-property-services")
                            .queryParam("hotelPropertyId", hotelPropertyId)
                            .build())
                    .retrieve()
                    .bodyToFlux(HotelPropertyServices.class)
                    .collectList()
                    .block();
            return list == null ? Collections.emptyList() : list;
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }
}
