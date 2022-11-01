package com.andreyenka.eventstask.service.impl;

import com.andreyenka.eventstask.exception_handling.ResourceNotFoundException;
import com.andreyenka.eventstask.repository.EventRepository;
import com.andreyenka.eventstask.repository.entity.Event;
import com.andreyenka.eventstask.service.EventService;
import com.andreyenka.eventstask.service.dto.EventDto;
import com.andreyenka.eventstask.service.dto.ObjectMapperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("eventService")
@Transactional
@Log4j2
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {


    private final EventRepository eventRepository;
    private final ObjectMapperService mapper;

    @Override
    public EventDto create(EventDto eventDto) {
        log.debug("Create event={} in database event", eventDto);
        Event eventCreated = mapper.toEntity(eventDto);
        eventRepository.create(eventCreated);
        return mapper.toDto(eventCreated);
    }

    @Override
    public EventDto findById(Long id) {
        log.debug("Get event by id={} from database events", id);
        Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " wasn't found"));
        return mapper.toDto(event);
    }

    @Override
    public List<EventDto> findAll() {
        log.debug("Get all events from database events");
        List<Event> events = eventRepository.findAll();
        return events.stream().map(mapper::toDto).toList();

    }

    @Override
    public EventDto update(EventDto eventDto) {
        log.debug("Update event={} in database events", eventDto);
        Event eventUpdated = mapper.toEntity(eventDto);
        eventRepository.update(eventUpdated);
        return mapper.toDto(eventUpdated);
    }

    @Override
    public void delete(Long id) {
        log.debug("Delete event by id={} in database events", id);
        if (!eventRepository.delete(id)) {
            throw new ResourceNotFoundException("No event with id: " + id);
        }
    }

}
