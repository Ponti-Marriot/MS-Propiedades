package com.pontimarriot.ms_propiedades.Repositories;

import com.pontimarriot.ms_propiedades.Entities.Image;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class ImageRepository {

    private final WebClient webClient;
    private final String baseUrl = "/images";

    public ImageRepository(@Qualifier("propertiesWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Image> findAll() {
        return webClient.get()
                .uri(baseUrl + "/imageList")
                .retrieve()
                .bodyToFlux(Image.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Mono<Image> findById(UUID id) {
        return webClient.get()
                .uri(baseUrl + "/{id}", id)
                .retrieve()
                .bodyToMono(Image.class)
                .onErrorResume(e -> Mono.empty());
    }

    public Flux<Image> findByPropertyId(UUID propertyId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(baseUrl + "/byProperty").queryParam("propertyId", propertyId).build())
                .retrieve()
                .bodyToFlux(Image.class)
                .onErrorResume(e -> Flux.empty());
    }

    public Mono<Image> save(Image image) {
        return webClient.post()
                .uri(baseUrl)
                .bodyValue(image)
                .retrieve()
                .bodyToMono(Image.class)
                .onErrorResume(e -> Mono.empty());
    }

    public Mono<Void> deleteById(UUID id) {
        return webClient.delete()
                .uri(baseUrl + "/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(e -> Mono.empty());
    }
}
