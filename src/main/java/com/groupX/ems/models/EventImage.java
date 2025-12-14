package com.groupX.ems.models;

public class EventImage {
    private long id;
    private long eventId;
    private String fileName;
    private String mimeType;
    private byte[] data;

    public EventImage() {
    }

    public EventImage(long id, long eventId, String fileName, String mimeType, byte[] data) {
        this.id = id;
        this.eventId = eventId;
        this.fileName = fileName;
        this.mimeType = mimeType;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

