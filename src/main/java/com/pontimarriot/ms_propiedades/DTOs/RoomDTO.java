package com.pontimarriot.ms_propiedades.DTOs;

import java.time.LocalDateTime;
import java.util.UUID;

public record RoomDTO(
        UUID id,
        String title,
        String description,
        String roomType,
        UUID availabilityDatesId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
