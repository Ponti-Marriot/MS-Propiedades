package com.pontimarriot.ms_propiedades.DTOs;

import java.util.UUID;

public record RoomServicesDTO(
        UUID serviceId,
        UUID roomId,
        String createdAt
) {
}
