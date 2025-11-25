package com.pontimarriot.ms_propiedades.Mappers;

import com.pontimarriot.ms_propiedades.DTOs.AvailabilityDatesDTO;
import com.pontimarriot.ms_propiedades.Entities.AvailabilityDates;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class AvailabilityDatesMapper {

    public static AvailabilityDatesDTO toDTO(AvailabilityDates entity) {
        if (entity == null) return null;
        return new AvailabilityDatesDTO(
                entity.getId(),
                entity.getRoomId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getCreatedAt()
        );
    }

    public static AvailabilityDates toEntity(AvailabilityDatesDTO dto) {
        if (dto == null) return null;
        return new AvailabilityDates(
                dto.roomId(),
                dto.startDate(),
                dto.endDate(),
                dto.createdAt()
        );
    }

    public static List<AvailabilityDatesDTO> toDTOList(List<AvailabilityDates> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(AvailabilityDatesMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<AvailabilityDates> toEntityList(List<AvailabilityDatesDTO> dtos) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(AvailabilityDatesMapper::toEntity)
                .collect(Collectors.toList());
    }
}
