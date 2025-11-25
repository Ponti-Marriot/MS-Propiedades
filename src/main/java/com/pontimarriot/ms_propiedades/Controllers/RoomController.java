package com.pontimarriot.ms_propiedades.Controllers;

import com.pontimarriot.ms_propiedades.DTOs.RoomDTO;
import com.pontimarriot.ms_propiedades.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/list")
    public Flux<RoomDTO> listRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping
    public Mono<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO) {
        return roomService.createRoom(roomDTO);
    }
}
