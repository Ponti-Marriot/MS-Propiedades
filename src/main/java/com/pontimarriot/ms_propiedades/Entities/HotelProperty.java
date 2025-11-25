package com.pontimarriot.ms_propiedades.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class HotelProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private int stars;
    private String property_type;
    private UUID location_id;
    private String address;
    private UUID images_id;

    public HotelProperty(){
    }

    public HotelProperty(String name, int stars, String property_type, UUID location_id, String address, UUID images_id) {
        this.name = name;
        this.stars = stars;
        this.property_type = property_type;
        this.location_id = location_id;
        this.address = address;
        this.images_id = images_id;
    }
}
