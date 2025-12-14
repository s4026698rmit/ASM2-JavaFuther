package com.groupX.ems.services;

import com.groupX.ems.models.Session;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    Optional<Session> findById(long id);
    List<Session> findAll();
    Session save(Session session);
    void delete(long id);
}

