package com.pontimarriot.ms_propiedades.Mappers;

import com.pontimarriot.ms_propiedades.DTOs.RoomServicesDTO;
import com.pontimarriot.ms_propiedades.Entities.RoomServices;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class RoomServicesMapper {

    public static RoomServicesDTO toDTO(RoomServices entity) {
        if (entity == null) return null;
        String createdAt = entity.getCreated_at() == null ? Instant.now().toString() : entity.getCreated_at();
        return new RoomServicesDTO(
                entity.getId(),
                entity.getService_id(),
                entity.getRoom_id(),
                createdAt
        );
    }

    public static RoomServices toEntity(RoomServicesDTO dto) {
        if (dto == null) return null;
        String createdAt = dto.created_at() == null ? Instant.now().toString() : dto.created_at();
        return new RoomServices(
                dto.service_id(),
                dto.room_id(),
                createdAt
        );
    }

    public static List<RoomServicesDTO> toDTOList(List<RoomServices> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(RoomServicesMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<RoomServices> toEntityList(List<RoomServicesDTO> dtos) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(RoomServicesMapper::toEntity)
                .collect(Collectors.toList());
    }
}
