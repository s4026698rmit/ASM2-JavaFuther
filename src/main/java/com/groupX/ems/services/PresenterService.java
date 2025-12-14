package com.groupX.ems.services;

import com.groupX.ems.models.Presenter;

import java.util.List;

public interface PresenterService {
    List<Presenter> findPublicPresentersByEventId(long eventId);
}

