package com.pontimarriot.ms_propiedades.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class AvailabilityDates {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID hotelpropertiesrooms_id;
    private LocalDate start_date;
    private LocalDate finish_date;
    private String created_at;

    public AvailabilityDates() {
    }

    public AvailabilityDates(UUID hotelpropertiesrooms_id, LocalDate start_date, LocalDate finish_date, String created_at) {
        this.hotelpropertiesrooms_id = hotelpropertiesrooms_id;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.created_at = created_at;
    }
}