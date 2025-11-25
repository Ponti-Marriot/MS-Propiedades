package com.pontimarriot.ms_propiedades.DTOs;

import java.time.LocalDate;
import java.util.UUID;

public record AvailabilityDatesDTO(
        UUID id,
        UUID hotelpropertiesrooms_id,
        LocalDate start_date,
        LocalDate finish_date,
        String created_at
) {
}
