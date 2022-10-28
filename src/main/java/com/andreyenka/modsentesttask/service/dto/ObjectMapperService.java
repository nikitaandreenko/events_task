package com.andreyenka.modsentesttask.service.dto;

import com.andreyenka.modsentesttask.repository.entity.Address;
import com.andreyenka.modsentesttask.repository.entity.Event;
import com.andreyenka.modsentesttask.repository.entity.Location;
import com.andreyenka.modsentesttask.repository.entity.Organizer;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperService {

    public AddressDto toDto(Address entity) {
        AddressDto dto = new AddressDto();
        dto.setId(entity.getId());
        dto.setCountry(entity.getCountry());
        dto.setCity(entity.getCity());
        dto.setStreet(entity.getStreet());
        dto.setHouse(entity.getHouse());
        dto.setRoom(entity.getRoom());
        return dto;
    }

    public Address toEntity(AddressDto dto) {
        Address entity = new Address();
        entity.setId(dto.getId());
        entity.setCountry(dto.getCountry());
        entity.setCity(dto.getCity());
        entity.setStreet(dto.getStreet());
        entity.setHouse(dto.getHouse());
        entity.setRoom(dto.getRoom());
        return entity;
    }

    public LocationDto toDto(Location entity) {
        LocationDto dto = new LocationDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(toDto(entity.getAddress()));
        return dto;
    }

    public Location toEntity(LocationDto dto) {
        Location entity = new Location();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAddress(toEntity(dto.getAddress()));
        return entity;
    }

    public OrganizerDto toDto(Organizer entity) {
        OrganizerDto dto = new OrganizerDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setAddress(toDto(entity.getAddress()));
        return dto;
    }

    public Organizer toEntity(OrganizerDto dto) {
        Organizer entity = new Organizer();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setAddress(toEntity(dto.getAddress()));
        return entity;
    }

    public EventDto toDto(Event entity) {
        EventDto dto = new EventDto();
        dto.setId(entity.getId());
        dto.setTopic(EventDto.Topic.valueOf(entity.getTopic().toString()));
        dto.setDescription(entity.getDescription());
        dto.setLocation(toDto(entity.getLocation()));
        dto.setOrganizer(toDto(entity.getOrganizer()));
        dto.setDateTime(entity.getDateTime());
        dto.setStatus(EventDto.Status.valueOf(entity.getStatus().toString()));
        return dto;
    }

    public Event toEntity(EventDto dto) {
        Event entity = new Event();
        entity.setId(dto.getId());
        entity.setTopic(Event.Topic.valueOf(dto.getTopic().toString()));
        entity.setDescription(dto.getDescription());
        entity.setLocation(toEntity(dto.getLocation()));
        entity.setOrganizer(toEntity(dto.getOrganizer()));
        entity.setDateTime(dto.getDateTime());
        entity.setStatus(Event.Status.valueOf(dto.getStatus().toString()));
        return entity;
    }

}
