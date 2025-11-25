package com.pontimarriot.ms_propiedades.Services;

import com.pontimarriot.ms_propiedades.DTOs.HotelPropertyDTO;
import com.pontimarriot.ms_propiedades.Mappers.HotelPropertyMapper;
import com.pontimarriot.ms_propiedades.Repositories.HotelPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
public class PropertiesService {

    @Autowired
    private HotelPropertyRepository hotelPropertyRepository;

    // Return a Mono of single DTO (reactive, non-blocking)
    public Mono<HotelPropertyDTO> getPropertyById(UUID propertyId) {
        return hotelPropertyRepository.findById(propertyId)
                .map(HotelPropertyMapper::toDTO);
    }

    // Return a Flux of DTOs (streaming, non-blocking)
    public Flux<HotelPropertyDTO> getAllPropertiesStream() {
        return hotelPropertyRepository.findAll()
                .map(HotelPropertyMapper::toDTO);
    }

    // If you need a single List result, return a Mono<List<DTO>> (still non-blocking)
    public Mono<List<HotelPropertyDTO>> getAllProperties() {
        return hotelPropertyRepository.findAll()
                .map(HotelPropertyMapper::toDTO)
                .collectList();
    }

    public Mono<HotelPropertyDTO> createProperty(HotelPropertyDTO propertyDTO) {
        return hotelPropertyRepository.save(HotelPropertyMapper.toEntity(propertyDTO))
                .map(HotelPropertyMapper::toDTO);
    }
}
