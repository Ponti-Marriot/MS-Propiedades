package com.pontimarriot.ms_propiedades.DTOs;

import java.util.UUID;

public record ServiceDTO(
        UUID id,
        String name,
        String category,
        String description
) {
}
