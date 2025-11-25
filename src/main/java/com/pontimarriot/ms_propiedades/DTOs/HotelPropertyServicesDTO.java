package com.pontimarriot.ms_propiedades.DTOs;

import java.util.UUID;

public record HotelPropertyServicesDTO(
        UUID id,
        UUID serviceId,
        UUID hotelPropertyId,
        String createdAt
) {
}
