package com.pontimarriot.ms_propiedades.Services;

import com.pontimarriot.ms_propiedades.DTOs.AvailabilityQueryDTO;
import com.pontimarriot.ms_propiedades.DTOs.PropertyAndRoomsResponseDTO;
import com.pontimarriot.ms_propiedades.DTOs.RoomsResponseDTO;
import com.pontimarriot.ms_propiedades.Entities.Image;
import com.pontimarriot.ms_propiedades.Entities.Location;
import com.pontimarriot.ms_propiedades.Entities.Room;
import com.pontimarriot.ms_propiedades.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AvailabilityService {

    @Autowired
    HotelPropertyRepository hotelPropertyRepository;
    @Autowired
    HotelPropertyRoomRepository hotelPropertyRoomRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelPropertyServicesRepository hotelPropertyServicesRepository;
    @Autowired
    AvailabilityDatesRepository availabilityDatesRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    RoomServicesRepository roomServicesRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    ImageRepository imageRepository;

    public Flux<PropertyAndRoomsResponseDTO> checkRoomsAvailability(UUID roomId, AvailabilityQueryDTO availabilityQueryDTO) {
        return hotelPropertyRepository.findAll()
                .flatMap(hp -> {
                    Mono<Location> locationMono = hp.getLocation_id() != null
                            ? locationRepository.findById(hp.getLocation_id()).defaultIfEmpty(new Location())
                            : Mono.just(new Location());

                    Mono<List<String>> serviciosMono = hotelPropertyServicesRepository.findByHotelPropertyId(hp.getId())
                            .flatMap(hps -> serviceRepository.findById(hps.getService_id())
                                    .map(com.pontimarriot.ms_propiedades.Entities.Service::getName))
                            .collectList();

                    Mono<Image> imageMono = hp.getImages_id() != null
                            ? imageRepository.findById(hp.getImages_id()).defaultIfEmpty(new Image())
                            : Mono.just(new Image());

                    LocalDate reqCheckIn = availabilityQueryDTO.checkInDate();
                    LocalDate reqCheckOut = availabilityQueryDTO.checkOutDate();

                    // For each HotelPropertyRoom: check availability dates for that hotelpropertiesrooms_id,
                    // skip if any availability date overlaps the requested period, otherwise include room.
                    Flux<RoomsResponseDTO> habitacionesFlux = hotelPropertyRoomRepository.findByHotelPropertyId(hp.getId())
                            .flatMap(hpr ->
                                    // gather availability dates for this specific HotelPropertyRoom
                                    availabilityDatesRepository.findAll()
                                            .filter(ad -> ad != null && hpr.getId() != null && hpr.getId().equals(ad.getHotelpropertiesrooms_id()))
                                            .collectList()
                                            .flatMapMany(avails -> {
                                                boolean hasConflict = avails.stream().anyMatch(ad -> {
                                                    LocalDate begin = ad.getStart_date();
                                                    LocalDate end = ad.getFinish_date();
                                                    // overlap if intervals intersect
                                                    return !(reqCheckOut.isBefore(begin) || reqCheckIn.isAfter(end));
                                                });

                                                if (hasConflict) {
                                                    // don't include this room
                                                    return Flux.empty();
                                                }

                                                // no conflicts:
                                                // fetch Room and room services -> resolve service names -> build RoomsResponseDTO
                                                Mono<RoomsResponseDTO> rrMono = roomRepository.findById(hpr.getRoom_id())
                                                        .defaultIfEmpty(new Room())
                                                        .flatMap(room ->
                                                                roomServicesRepository.findByRoomId(hpr.getRoom_id())
                                                                        .flatMap(rs -> serviceRepository.findById(rs.getService_id())
                                                                                .map(com.pontimarriot.ms_propiedades.Entities.Service::getName))
                                                                        .collectList()
                                                                        .map(serviceNames -> {
                                                                            RoomsResponseDTO rr = new RoomsResponseDTO();
                                                                            rr.setHabitacion_id(hpr.getRoom_id());
                                                                            rr.setTipo(room.getRoom_type());
                                                                            rr.setCodigo_tipo_habitacion(room.getRoom_type());
                                                                            rr.setPrecio(hpr.getPrice_per_night());
                                                                            rr.setDisponible(true);
                                                                            rr.setServicios_habitacion(serviceNames != null ? serviceNames : new ArrayList<>());
                                                                            return rr;
                                                                        })
                                                        );

                                                return rrMono.flux();
                                            })
                            );

                    Mono<List<RoomsResponseDTO>> habitacionesMono = habitacionesFlux.collectList();

                    return Mono.zip(locationMono, serviciosMono, imageMono, habitacionesMono)
                            .map(tuple -> {
                                Location loc = tuple.getT1();
                                List<String> servicios = tuple.getT2();
                                Image img = tuple.getT3();
                                List<RoomsResponseDTO> habitaciones = tuple.getT4();

                                PropertyAndRoomsResponseDTO dto = new PropertyAndRoomsResponseDTO();
                                dto.setHotel_id(hp.getId());
                                dto.setNombre(hp.getName());
                                dto.setCategoria_estrellas(hp.getStars());
                                dto.setDireccion(hp.getAddress());
                                dto.setCiudad(loc != null ? loc.getCity_name() : null);
                                dto.setServicios_hotel(servicios != null ? servicios : new ArrayList<>());

                                List<String> fotos = new ArrayList<>();
                                if (img != null && img.getUrl() != null) {
                                    fotos.add(img.getUrl());
                                }
                                dto.setFotos(fotos);

                                dto.setHabitaciones(habitaciones != null ? habitaciones : new ArrayList<>());

                                return dto;
                            });
                });
    }
}
