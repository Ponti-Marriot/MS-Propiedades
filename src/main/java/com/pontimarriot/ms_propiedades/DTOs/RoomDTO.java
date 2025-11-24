package com.pontimarriot.ms_propiedades.DTOs;

import java.time.LocalDateTime;
import java.util.UUID;

public record RoomDTO(
        String title,
        String description,
        String roomType,
        UUID availabilityDatesId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
