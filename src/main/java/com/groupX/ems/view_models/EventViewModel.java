package com.groupX.ems.view_models;

public class EventViewModel {
    private long id;
    private String name;
    private String type;
    private String dateRange;
    private String location;
    private String status;
    private String availabilityText;

    public EventViewModel() {
    }

    public EventViewModel(long id,
                          String name,
                          String type,
                          String dateRange,
                          String location,
                          String status,
                          String availabilityText) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.dateRange = dateRange;
        this.location = location;
        this.status = status;
        this.availabilityText = availabilityText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvailabilityText() {
        return availabilityText;
    }

    public void setAvailabilityText(String availabilityText) {
        this.availabilityText = availabilityText;
    }
}

