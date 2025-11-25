package com.pontimarriot.ms_propiedades.DTOs;

import java.util.UUID;

public record HotelPropertyServicesDTO(
        UUID id,
        UUID service_id,
        UUID hotel_property_id
) {
}
