package com.pontimarriot.ms_propiedades.Mappers;

import com.pontimarriot.ms_propiedades.DTOs.HotelPropertyRoomDTO;
import com.pontimarriot.ms_propiedades.Entities.HotelPropertyRoom;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class HotelPropertyRoomMapper {

    public static HotelPropertyRoomDTO toDTO(HotelPropertyRoom entity) {
        if (entity == null) return null;
        return new HotelPropertyRoomDTO(
                entity.getId(),
                entity.getHotel_property_id(),
                entity.getRoom_id(),
                entity.getBedrooms(),
                entity.getBathrooms(),
                entity.getPrice_per_night()
        );
    }

    public static HotelPropertyRoom toEntity(HotelPropertyRoomDTO dto) {
        if (dto == null) return null;
        return new HotelPropertyRoom(
                dto.hotel_property_id(),
                dto.room_id(),
                dto.bedrooms(),
                dto.bathrooms(),
                dto.price_per_night()
        );
    }

    public static List<HotelPropertyRoomDTO> toDTOList(List<HotelPropertyRoom> entities) {
        if (entities == null) return Collections.emptyList();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(HotelPropertyRoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<HotelPropertyRoom> toEntityList(List<HotelPropertyRoomDTO> dtos) {
        if (dtos == null) return Collections.emptyList();
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(HotelPropertyRoomMapper::toEntity)
                .collect(Collectors.toList());
    }
}
