package com.andreyenka.modsentesttask.controller;

import com.andreyenka.modsentesttask.service.EventService;
import com.andreyenka.modsentesttask.service.dto.AddressDto;
import com.andreyenka.modsentesttask.service.dto.EventDto;
import com.andreyenka.modsentesttask.service.dto.LocationDto;
import com.andreyenka.modsentesttask.service.dto.OrganizerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    EventService eventService;

    private EventDto eventFirst;
    private EventDto eventSecond;

    private List<EventDto> events;
    private AddressDto addressOrganizationFirst;
    private AddressDto addressLocationFirst;

    private LocationDto locationFirst;

    private OrganizerDto organizerFirst;

    private AddressDto addressLocationSecond;

    private AddressDto addressOrganizationSecond;

    private LocationDto locationSecond;

    private OrganizerDto organizerSecond;


    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        addressLocationFirst = new AddressDto();
        addressLocationFirst.setId(1L);
        addressLocationFirst.setCountry("England");
        addressLocationFirst.setCity("London");
        addressLocationFirst.setStreet("Lombard Road");
        addressLocationFirst.setHouse("8");
        addressLocationFirst.setRoom("-");
        addressOrganizationFirst = new AddressDto();
        addressOrganizationFirst.setId(2L);
        addressOrganizationFirst.setCountry("England");
        addressOrganizationFirst.setCity("London");
        addressOrganizationFirst.setStreet("Trafalgar Square");
        addressOrganizationFirst.setHouse("5");
        addressOrganizationFirst.setRoom("-");
        locationFirst = new LocationDto();
        locationFirst.setId(1L);
        locationFirst.setName("Ochre");
        locationFirst.setAddress(addressLocationFirst);
        organizerFirst = new OrganizerDto();
        organizerFirst.setId(1L);
        organizerFirst.setName("Quirky Group");
        organizerFirst.setEmail("hello@quirky-group.co.uk");
        organizerFirst.setAddress(addressOrganizationFirst);
        eventFirst = new EventDto();
        eventFirst.setId(1L);
        eventFirst.setTopic(EventDto.Topic.BIRTHDAY);
        eventFirst.setStatus(EventDto.Status.IN_WORK);
        eventFirst.setOrganizer(organizerFirst);
        eventFirst.setLocation(locationFirst);
        eventFirst.setDescription("wedding between Collin Gerber and Jessy Peterson");
        eventFirst.setDateTime(LocalDateTime.of(2022, 12, 19, 14, 0, 1));
        addressLocationSecond = new AddressDto();
        addressLocationSecond.setId(1L);
        addressLocationSecond.setCountry("England");
        addressLocationSecond.setCity("London");
        addressLocationSecond.setStreet("Lombard Road");
        addressLocationSecond.setHouse("8");
        addressLocationSecond.setRoom("-");
        addressOrganizationSecond = new AddressDto();
        addressLocationSecond.setId(2L);
        addressLocationSecond.setCountry("England");
        addressLocationSecond.setCity("London");
        addressLocationSecond.setStreet("Trafalgar Square");
        addressLocationSecond.setHouse("5");
        addressLocationSecond.setRoom("-");
        locationSecond = new LocationDto();
        locationSecond.setId(1L);
        locationSecond.setName("Ochre");
        locationSecond.setAddress(addressLocationSecond);
        organizerSecond = new OrganizerDto();
        organizerSecond.setId(1L);
        organizerSecond.setName("Quirky Group");
        organizerSecond.setEmail("hello@quirky-group.co.uk");
        organizerSecond.setAddress(addressOrganizationSecond);
        eventSecond = new EventDto();
        eventSecond.setId(2L);
        eventSecond.setTopic(EventDto.Topic.CORPORATE);
        eventSecond.setStatus(EventDto.Status.IN_WORK);
        eventSecond.setOrganizer(organizerSecond);
        eventSecond.setLocation(locationSecond);
        eventSecond.setDescription("Corporate Google company");
        eventSecond.setDateTime(LocalDateTime.of(2022, 12, 30, 20, 0, 1));
        events = new ArrayList<>();
        events.add(eventFirst);
        events.add(eventSecond);
    }

    @Test
    void getEventById() throws Exception {
        when(eventService.findById(any())).thenReturn(eventSecond);
        mockMvc.perform(
                        get("/api/events/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.topic").value("CORPORATE"))
                .andExpect(jsonPath("$.status").value("IN_WORK"))
                .andExpect(jsonPath("$.organizer").value(organizerSecond))
                .andExpect(jsonPath("$.location").value(locationSecond))
                .andExpect(jsonPath("$.description").value("Corporate Google company"))
                .andExpect(jsonPath("$.dateTime").value(LocalDateTime.of(2022, 12, 30, 20, 0, 1).toString()));


    }

    @Test
    void addEvent() throws Exception {
        when(eventService.create(any())).thenReturn(eventFirst);
        mockMvc.perform(
                        post("/api/events")
                                .content(objectMapper.writeValueAsString(eventFirst))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(eventFirst)));
    }

    @Test
    void updateEvent() throws Exception {
        when(eventService.update(any())).thenReturn(eventSecond);
        when(eventService.findById(any())).thenReturn(eventSecond);
        mockMvc.perform(
                        put("/api/events/")
                                .content(objectMapper.writeValueAsString(new EventDto()))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.topic").value("CORPORATE"))
                .andExpect(jsonPath("$.status").value("IN_WORK"))
                .andExpect(jsonPath("$.organizer").value(organizerSecond))
                .andExpect(jsonPath("$.location").value(locationSecond))
                .andExpect(jsonPath("$.description").value("Corporate Google company"))
                .andExpect(jsonPath("$.dateTime").value(LocalDateTime.of(2022, 12, 30, 20, 0, 1).toString()));
    }


    @Test
    void getAllEvents() throws Exception {
        when(eventService.findAll()).thenReturn(events);
        mockMvc.perform(
                        get("/api/events"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(events)));
    }

    @Test
    void deleteEvent() throws Exception {
        when(eventService.findById(any())).thenReturn(eventSecond);
        mockMvc.perform(
                        delete("/api/events/2"))
                .andExpect(status().isOk());
    }
}