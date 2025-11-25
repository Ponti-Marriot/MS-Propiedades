package com.pontimarriot.ms_propiedades.DTOs;

import java.util.UUID;

public record StatusDTO(
        UUID id,
        String name,
        String created_at
) {
}
