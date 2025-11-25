package com.pontimarriot.ms_propiedades.Services;

import com.netflix.discovery.converters.Auto;
import com.pontimarriot.ms_propiedades.DTOs.HotelPropertyRoomDTO;
import com.pontimarriot.ms_propiedades.Entities.HotelPropertyRoom;
import com.pontimarriot.ms_propiedades.Mappers.HotelPropertyRoomMapper;
import com.pontimarriot.ms_propiedades.Repositories.HotelPropertyRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@Service
public class PropertyRoomsService {

    @Autowired
    HotelPropertyRoomRepository hotelPropertyRoomRepository;

    public Mono<HotelPropertyRoomDTO> addRoomToProperty(HotelPropertyRoomDTO hotelPropertyRoomDTO) {
        HotelPropertyRoom hotelPropertyRoom;
        hotelPropertyRoom = HotelPropertyRoomMapper.toEntity(hotelPropertyRoomDTO);
        return hotelPropertyRoomRepository.save(hotelPropertyRoom)
                .map(HotelPropertyRoomMapper::toDTO);
    }
}
