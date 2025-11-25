package com.pontimarriot.ms_propiedades.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    private String room_type;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Room(){
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }
    public Room(String title, String description, String room_type) {
        this.title = title;
        this.description = description;
        this.room_type = room_type;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }
}
