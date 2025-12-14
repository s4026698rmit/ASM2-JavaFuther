package com.groupX.ems.services;

import com.groupX.ems.models.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventService {
    Optional<Event> findById(long id);
    List<Event> findAll();
    List<Event> getAllPublicEvents();
    List<Event> findPublicEvents(String eventType,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 String location,
                                 boolean onlyAvailable);
    Event save(Event event);
    void delete(long id);
}

