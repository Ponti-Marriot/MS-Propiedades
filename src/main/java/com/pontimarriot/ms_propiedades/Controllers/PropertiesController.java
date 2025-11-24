package com.pontimarriot.ms_propiedades.Controllers;

import com.pontimarriot.ms_propiedades.DTOs.HotelPropertyDTO;
import com.pontimarriot.ms_propiedades.Services.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertiesController {
    @Autowired
    private PropertiesService propertiesService;

    @GetMapping("/propertyList")
    public List<HotelPropertyDTO> getProperties() {
        return propertiesService.getAllProperties();
    }
}