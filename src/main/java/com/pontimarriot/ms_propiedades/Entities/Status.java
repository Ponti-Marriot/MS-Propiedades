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
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String created_at;

    public Status() {
    }

    public Status(String name, String created_at) {
        this.name = name;
        this.created_at = created_at;
    }
}
