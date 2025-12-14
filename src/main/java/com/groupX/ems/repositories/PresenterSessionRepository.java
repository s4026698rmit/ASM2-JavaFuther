package com.groupX.ems.repositories;

import com.groupX.ems.models.PresenterSession;

import java.util.List;

public interface PresenterSessionRepository {
    void addPresenterToSession(long presenterId, long sessionId);
    void removePresenterFromSession(long presenterId, long sessionId);
    List<PresenterSession> findBySessionId(long sessionId);
    List<PresenterSession> findByPresenterId(long presenterId);
}

