package com.pontimarriot.ms_propiedades.DTOs;

import java.time.LocalDate;
import java.util.UUID;

public record AvailabilityDatesDTO(
        UUID id,
        UUID roomId,
        LocalDate startDate,
        LocalDate endDate,
        String createdAt
) {
}
