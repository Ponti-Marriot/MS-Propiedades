package com.pontimarriot.ms_propiedades.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class HotelPropertyServices {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID service_id;
    private UUID hotel_property_id;

    public HotelPropertyServices() {
    }
    public HotelPropertyServices(UUID service_id, UUID hotel_property_id) {
        this.service_id = service_id;
        this.hotel_property_id = hotel_property_id;
    }
}
