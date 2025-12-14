package com.groupX.ems.models;

public class PresenterSession {
    private long presenterId;
    private long sessionId;

    public PresenterSession() {
    }

    public PresenterSession(long presenterId, long sessionId) {
        this.presenterId = presenterId;
        this.sessionId = sessionId;
    }

    public long getPresenterId() {
        return presenterId;
    }

    public void setPresenterId(long presenterId) {
        this.presenterId = presenterId;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }
}

