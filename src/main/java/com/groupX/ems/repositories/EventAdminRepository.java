package com.groupX.ems.repositories;

import com.groupX.ems.models.EventAdmin;

import java.util.List;
import java.util.Optional;

public interface EventAdminRepository {
    Optional<EventAdmin> findById(long id);
    List<EventAdmin> findAll();
    EventAdmin save(EventAdmin eventAdmin);
    void delete(long id);
}

