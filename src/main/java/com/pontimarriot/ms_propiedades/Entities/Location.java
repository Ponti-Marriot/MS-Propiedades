// java
package com.pontimarriot.ms_propiedades.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "locations")
public class Location {

    @Id
    private UUID id;

    @Column(name = "country_code")
    private String country_code;

    @Column(name = "state")
    private String state;

    @Column(name = "city_code")
    private String city_code;

    @Column(name = "city_name")
    private String city_name;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "subdiv_code")
    private String subdiv_code;

    public Location() {
    }

    public Location(UUID id, String country_code, String state, String city_code, String city_name,
                    String timezone, String subdiv_code) {
        this.id = id;
        this.country_code = country_code;
        this.state = state;
        this.city_code = city_code;
        this.city_name = city_name;
        this.timezone = timezone;
        this.subdiv_code = subdiv_code;
    }

}
