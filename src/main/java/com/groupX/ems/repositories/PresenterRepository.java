package com.groupX.ems.repositories;

import com.groupX.ems.models.Presenter;

import java.util.List;
import java.util.Optional;

public interface PresenterRepository {
    Optional<Presenter> findById(long id);
    List<Presenter> findAll();
    Presenter save(Presenter presenter);
    void delete(long id);
}

