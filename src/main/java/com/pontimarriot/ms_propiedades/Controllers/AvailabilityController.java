package com.pontimarriot.ms_propiedades.Controllers;

import com.netflix.discovery.converters.Auto;
import com.pontimarriot.ms_propiedades.DTOs.AvailabilityQueryDTO;
import com.pontimarriot.ms_propiedades.DTOs.HotelPropertyDTO;
import com.pontimarriot.ms_propiedades.DTOs.PropertyAndRoomsResponseDTO;
import com.pontimarriot.ms_propiedades.DTOs.RoomDTO;
import com.pontimarriot.ms_propiedades.Services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/rooms")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping("/{roomId}/availability")
    public Flux<PropertyAndRoomsResponseDTO> checkRoomAvailability(@RequestBody AvailabilityQueryDTO availabilityQueryDTO, @PathVariable("roomId")UUID roomId) {
        return availabilityService.checkRoomsAvailability(roomId, availabilityQueryDTO);
    }
}
