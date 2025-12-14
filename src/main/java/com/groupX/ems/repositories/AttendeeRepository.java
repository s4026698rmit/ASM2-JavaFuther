package com.groupX.ems.repositories;

import com.groupX.ems.models.Attendee;

import java.util.List;
import java.util.Optional;

public interface AttendeeRepository {
    Optional<Attendee> findById(long id);
    List<Attendee> findAll();
    Attendee save(Attendee attendee);
    void delete(long id);
}

