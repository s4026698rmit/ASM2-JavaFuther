package com.groupX.ems.models;

import java.time.LocalDateTime;

public class Schedule {
    private long personId;
    private long sessionId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Schedule() {
    }

    public Schedule(long personId, long sessionId, LocalDateTime startTime, LocalDateTime endTime) {
        this.personId = personId;
        this.sessionId = sessionId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}

