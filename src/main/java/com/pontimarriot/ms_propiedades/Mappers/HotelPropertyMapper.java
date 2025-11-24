package com.pontimarriot.ms_propiedades.Mappers;

import com.pontimarriot.ms_propiedades.DTOs.HotelPropertyDTO;
import com.pontimarriot.ms_propiedades.Entities.HotelProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class HotelPropertyMapper {

    private HotelPropertyMapper() {}

    public static HotelPropertyDTO toDTO(HotelProperty entity) {
        if (entity == null) return null;

        LocalDateTime createdAt = entity.getCreatedAt() == null ? LocalDateTime.now() : entity.getCreatedAt();
        LocalDateTime updatedAt = entity.getUpdatedAt() == null ? LocalDateTime.now() : entity.getUpdatedAt();

        List<String> photos = entity.getPhotosUrls() == null
                ? Collections.emptyList()
                : new ArrayList<>(entity.getPhotosUrls());

        return new HotelPropertyDTO(
                entity.getName(),
                entity.getDescription(),
                entity.getSquareMeters(),
                entity.getStars(),
                entity.getPropertyType(),
                entity.getLocationId(),
                entity.getAddress(),
                entity.getAvailabilityDatesId(),
                createdAt,
                updatedAt,
                photos
        );
    }

    public static HotelProperty toEntity(HotelPropertyDTO dto) {
        if (dto == null) return null;

        HotelProperty entity = new HotelProperty();
        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setSquareMeters(dto.squareMeters());
        entity.setStars(dto.stars());
        entity.setPropertyType(dto.propertyType());
        entity.setLocationId(dto.locationId());
        entity.setAddress(dto.address());
        entity.setAvailabilityDatesId(dto.availabilityDatesId());

        entity.setCreatedAt(dto.createdAt() == null ? LocalDateTime.now() : dto.createdAt());
        entity.setUpdatedAt(dto.updatedAt() == null ? LocalDateTime.now() : dto.updatedAt());

        List<String> photos = dto.photosUrls() == null
                ? new ArrayList<>()
                : new ArrayList<>(dto.photosUrls());
        entity.setPhotosUrls(photos);

        return entity;
    }

    public static List<HotelPropertyDTO> toDTOList(List<HotelProperty> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(HotelPropertyMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<HotelProperty> toEntityList(List<HotelPropertyDTO> dtos) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(HotelPropertyMapper::toEntity)
                .collect(Collectors.toList());
    }
}
