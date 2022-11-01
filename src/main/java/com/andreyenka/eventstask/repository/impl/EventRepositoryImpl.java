package com.andreyenka.eventstask.repository.impl;

import com.andreyenka.eventstask.repository.EventRepository;
import com.andreyenka.eventstask.repository.entity.Event;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository("eventRepository")
@Transactional
public class EventRepositoryImpl implements EventRepository {

    public static final String FIND_ALL =
            "SELECT e FROM Event e JOIN FETCH e.organizer AS o JOIN FETCH o.address JOIN FETCH e.location AS l JOIN FETCH l.address";
    public static final String FIND_BY_ID =
            "SELECT e FROM Event e JOIN FETCH e.organizer AS o JOIN FETCH o.address JOIN FETCH e.location AS l JOIN FETCH l.address WHERE e.id = :id";

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Event create(Event entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(entityManager.createQuery(FIND_BY_ID, Event.class)
                .setParameter("id", id).getSingleResult());
    }

    @Override
    public List<Event> findAll() {
        return entityManager.createQuery(FIND_ALL, Event.class).getResultList();
    }

    @Override
    public Event update(Event entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public boolean delete(Long id) {
        Event event = entityManager.find(Event.class, id);
        if (event == null) {
            return false;
        }
        entityManager.remove(event);
        return true;
    }

}
