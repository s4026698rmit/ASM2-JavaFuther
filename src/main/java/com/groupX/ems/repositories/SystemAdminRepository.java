package com.groupX.ems.repositories;

import com.groupX.ems.models.SystemAdmin;

import java.util.List;
import java.util.Optional;

public interface SystemAdminRepository {
    Optional<SystemAdmin> findById(long id);
    List<SystemAdmin> findAll();
    SystemAdmin save(SystemAdmin systemAdmin);
    void delete(long id);
}

