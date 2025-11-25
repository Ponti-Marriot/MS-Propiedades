package com.pontimarriot.ms_propiedades.Services;

import com.pontimarriot.ms_propiedades.DTOs.RoomDTO;
import com.pontimarriot.ms_propiedades.Mappers.RoomMapper;
import com.pontimarriot.ms_propiedades.Repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Flux<RoomDTO> getAllRooms() {
        return roomRepository.findAll()
                .map(RoomMapper::toDTO);
    }

    public Mono<RoomDTO> createRoom(RoomDTO roomDTO) {
        return roomRepository.save(RoomMapper.toEntity(roomDTO))
                .map(RoomMapper::toDTO);
    }
}
