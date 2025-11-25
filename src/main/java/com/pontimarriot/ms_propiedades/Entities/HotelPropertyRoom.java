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
public class HotelPropertyRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID hotel_property_id;
    private UUID room_id;
    private int bedrooms;
    private int bathrooms;
    private double price_per_night;

    public HotelPropertyRoom() {
    }

    public HotelPropertyRoom(UUID hotel_property_id, UUID room_id, int bedrooms, int bathrooms, double price_per_night) {
        this.hotel_property_id = hotel_property_id;
        this.room_id = room_id;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.price_per_night = price_per_night;
    }
}