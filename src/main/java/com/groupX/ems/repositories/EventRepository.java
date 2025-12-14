package com.groupX.ems.repositories;

import com.groupX.ems.models.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {
    Optional<Event> findById(long id);
    List<Event> findAll();
    Event save(Event event);
    void delete(long id);
}

