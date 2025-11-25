package com.pontimarriot.ms_propiedades.Mappers;

import com.pontimarriot.ms_propiedades.DTOs.HotelPropertyServicesDTO;
import com.pontimarriot.ms_propiedades.Entities.HotelPropertyServices;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class HotelPropertyServicesMapper {

    public static HotelPropertyServicesDTO toDTO(HotelPropertyServices entity) {
        if (entity == null) return null;
        String createdAt = entity.getCreatedAt() == null ? Instant.now().toString() : entity.getCreatedAt();
        return new HotelPropertyServicesDTO(
                entity.getId(),
                entity.getServiceId(),
                entity.getHotelPropertyId(),
                createdAt
        );
    }

    public static HotelPropertyServices toEntity(HotelPropertyServicesDTO dto) {
        if (dto == null) return null;
        String createdAt = dto.createdAt() == null ? Instant.now().toString() : dto.createdAt();
        return new HotelPropertyServices(
                dto.serviceId(),
                dto.hotelPropertyId(),
                createdAt
        );
    }

    public static List<HotelPropertyServicesDTO> toDTOList(List<HotelPropertyServices> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(HotelPropertyServicesMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<HotelPropertyServices> toEntityList(List<HotelPropertyServicesDTO> dtos) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(HotelPropertyServicesMapper::toEntity)
                .collect(Collectors.toList());
    }
}
