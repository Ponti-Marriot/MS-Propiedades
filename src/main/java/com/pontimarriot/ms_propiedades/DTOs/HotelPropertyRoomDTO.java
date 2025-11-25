package com.pontimarriot.ms_propiedades.DTOs;

import java.util.UUID;

public record HotelPropertyRoomDTO(
        UUID id,
        UUID hotelPropertyId,
        UUID roomId,
        UUID statusId,
        double squareMeters,
        int bedrooms,
        int bathrooms,
        double pricePerNight
) {
}
