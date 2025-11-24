package com.pontimarriot.ms_propiedades.Mappers;

import com.pontimarriot.ms_propiedades.DTOs.RoomDTO;
import com.pontimarriot.ms_propiedades.Entities.Room;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class RoomMapper {

    private RoomMapper() {}

    public static RoomDTO toDTO(Room entity) {
        if (entity == null) return null;

        LocalDateTime createdAt = entity.getCreatedAt() == null ? LocalDateTime.now() : entity.getCreatedAt();
        LocalDateTime updatedAt = entity.getUpdatedAt() == null ? LocalDateTime.now() : entity.getUpdatedAt();

        return new RoomDTO(
                entity.getTitle(),
                entity.getDescription(),
                entity.getRoomType(),
                entity.getAvailabilityDatesId(),
                createdAt,
                updatedAt
        );
    }

    public static Room toEntity(RoomDTO dto) {
        if (dto == null) return null;

        Room entity = new Room(
                dto.title(),
                dto.description(),
                dto.roomType(),
                dto.availabilityDatesId()
        );

        entity.setCreatedAt(dto.createdAt() == null ? LocalDateTime.now() : dto.createdAt());
        entity.setUpdatedAt(dto.updatedAt() == null ? LocalDateTime.now() : dto.updatedAt());

        return entity;
    }

    public static List<RoomDTO> toDTOList(List<Room> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(RoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Room> toEntityList(List<RoomDTO> dtos) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(RoomMapper::toEntity)
                .collect(Collectors.toList());
    }
}
