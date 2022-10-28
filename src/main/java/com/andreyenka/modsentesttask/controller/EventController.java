package com.andreyenka.modsentesttask.controller;


import com.andreyenka.modsentesttask.service.EventService;
import com.andreyenka.modsentesttask.service.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @GetMapping("/{id}")
    public EventDto getEventById(@PathVariable Long id) {
        return eventService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto addEvent(@RequestBody EventDto dto) {
        return eventService.create(dto);
    }

    @GetMapping
    public List<EventDto> getAllEvents() {
        return eventService.findAll();
    }

    @PutMapping
    public EventDto updateEvent(@RequestBody EventDto dto) {
        return eventService.update(dto);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
        return "Event with id = " + id + " was deleted";
    }

}
