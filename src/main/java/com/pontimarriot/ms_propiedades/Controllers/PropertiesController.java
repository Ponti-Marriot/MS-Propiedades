package com.pontimarriot.ms_propiedades.Controllers;

import com.pontimarriot.ms_propiedades.DTOs.HotelPropertyDTO;
import com.pontimarriot.ms_propiedades.Services.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertiesController {
    @Autowired
    private PropertiesService propertiesService;

    // Return a Mono containing the whole list (non-blocking)
    @GetMapping("/propertyList")
    public Mono<List<HotelPropertyDTO>> getProperties() {
        return propertiesService.getAllProperties();
    }

    // Optional: streaming endpoint (non-blocking)
    @GetMapping("/propertyStream")
    public Flux<HotelPropertyDTO> getPropertiesStream() {
        return propertiesService.getAllPropertiesStream();
    }

    @PostMapping
    public Mono<HotelPropertyDTO> createProperty(@RequestBody HotelPropertyDTO propertyDTO) {
        return propertiesService.createProperty(propertyDTO);
    }
}