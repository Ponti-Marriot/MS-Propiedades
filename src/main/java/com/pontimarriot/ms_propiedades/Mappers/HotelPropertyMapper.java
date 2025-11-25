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

    public static HotelPropertyDTO toDTO(HotelProperty entity) {
        if (entity == null) return null;

        return new HotelPropertyDTO(
                entity.getId(),
                entity.getName(),
                entity.getStars(),
                entity.getProperty_type(),
                entity.getLocation_id(),
                entity.getAddress(),
                entity.getImages_id()
        );
    }

    public static HotelProperty toEntity(HotelPropertyDTO dto) {
        if (dto == null) return null;

        HotelProperty entity = new HotelProperty();
        entity.setName(dto.name());
        entity.setStars(dto.stars());
        entity.setProperty_type(dto.property_type());
        entity.setLocation_id(dto.location_id());
        entity.setAddress(dto.address());
        entity.setImages_id(dto.images_id());

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
