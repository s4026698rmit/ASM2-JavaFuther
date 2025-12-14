package com.groupX.ems.services;

import com.groupX.ems.models.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    Optional<Notification> findById(long id);
    List<Notification> findByPersonId(long personId);
    Notification save(Notification notification);
    void markAsRead(long id);
}

