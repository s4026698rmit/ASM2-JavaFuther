package com.groupX.ems.view_models;

import java.time.LocalDateTime;

public class SessionViewModel {
    private long id;
    private String title;
    private String venue;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public SessionViewModel() {
    }

    public SessionViewModel(long id, String title, String venue, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.title = title;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
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

