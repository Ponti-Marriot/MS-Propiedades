package com.pontimarriot.ms_propiedades.DTOs;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record HotelPropertyDTO(
        UUID id,
        String name,
        String description,
        int squareMeters,
        int stars,
        String propertyType,
        UUID locationId,
        String address,
        UUID availabilityDatesId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<String> photosUrls
) {}