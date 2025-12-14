package com.groupX.ems.repositories;

import com.groupX.ems.models.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository {
    Optional<Notification> findById(long id);
    List<Notification> findAll();
    List<Notification> findByPersonId(long personId);
    Notification save(Notification notification);
    void delete(long id);
}

