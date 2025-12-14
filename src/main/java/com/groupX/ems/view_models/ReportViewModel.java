package com.groupX.ems.view_models;

public class ReportViewModel {
    private String title;
    private String summary;

    public ReportViewModel() {
    }

    public ReportViewModel(String title, String summary) {
        this.title = title;
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}

