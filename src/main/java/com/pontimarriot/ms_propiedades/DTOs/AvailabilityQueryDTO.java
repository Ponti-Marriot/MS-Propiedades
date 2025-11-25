package com.pontimarriot.ms_propiedades.DTOs;

import java.time.LocalDate;

public record AvailabilityQueryDTO(
        LocalDate checkInDate,
        LocalDate checkOutDate,
        int numRooms
) {
}
