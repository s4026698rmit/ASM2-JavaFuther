package com.groupX.ems.services;

import com.groupX.ems.models.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> findByPersonId(long personId);
    List<Schedule> findBySessionId(long sessionId);
    Schedule save(Schedule schedule);
    void delete(long personId, long sessionId);
}

