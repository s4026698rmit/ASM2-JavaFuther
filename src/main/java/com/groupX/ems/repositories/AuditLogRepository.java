package com.groupX.ems.repositories;

import com.groupX.ems.models.AuditLog;

import java.util.List;
import java.util.Optional;

public interface AuditLogRepository {
    Optional<AuditLog> findById(long id);
    List<AuditLog> findAll();
    AuditLog save(AuditLog auditLog);
    void delete(long id);
}

