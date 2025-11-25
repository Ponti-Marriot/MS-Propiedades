package com.pontimarriot.ms_propiedades.Controllers;

import com.pontimarriot.ms_propiedades.DTOs.HotelPropertyRoomDTO;
import com.pontimarriot.ms_propiedades.Entities.Room;
import com.pontimarriot.ms_propiedades.Services.PropertyRoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/property-rooms")
public class PropertyRoomsController {

    @Autowired
    PropertyRoomsService propertyRoomsService;

    @PostMapping
    public Mono<HotelPropertyRoomDTO> addRoomToProperty(@RequestBody HotelPropertyRoomDTO hotelPropertyRoomDTO) {
        return propertyRoomsService.addRoomToProperty(hotelPropertyRoomDTO);
    }
}
