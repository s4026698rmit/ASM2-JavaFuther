package com.groupX.ems.models;

public class SessionMaterial {
    private long id;
    private long sessionId;
    private String title;
    private String fileName;
    private String mimeType;
    private String storageUrl;
    private long uploadedBy;

    public SessionMaterial() {
    }

    public SessionMaterial(long id, long sessionId, String title, String fileName, String mimeType, String storageUrl,
                           long uploadedBy) {
        this.id = id;
        this.sessionId = sessionId;
        this.title = title;
        this.fileName = fileName;
        this.mimeType = mimeType;
        this.storageUrl = storageUrl;
        this.uploadedBy = uploadedBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getStorageUrl() {
        return storageUrl;
    }

    public void setStorageUrl(String storageUrl) {
        this.storageUrl = storageUrl;
    }

    public long getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(long uploadedBy) {
        this.uploadedBy = uploadedBy;
    }
}

