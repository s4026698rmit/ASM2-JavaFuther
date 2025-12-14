package com.groupX.ems.repositories;

import com.groupX.ems.models.Session;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {
    Optional<Session> findById(long id);
    List<Session> findAll();
    Session save(Session session);
    void delete(long id);
}

