package com.groupX.ems.models;

import com.groupX.ems.models.enums.Role;

import java.time.LocalDate;

public class Presenter extends Person {
    private String expertiseArea;
    private String bio;

    public Presenter() {
        super();
    }

    public Presenter(long id, String fullName, LocalDate dob, String email, String phone, String username,
                     String passwordHash, Role role, String expertiseArea, String bio) {
        super(id, fullName, dob, email, phone, username, passwordHash, role);
        this.expertiseArea = expertiseArea;
        this.bio = bio;
    }

    public String getExpertiseArea() {
        return expertiseArea;
    }

    public void setExpertiseArea(String expertiseArea) {
        this.expertiseArea = expertiseArea;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}

