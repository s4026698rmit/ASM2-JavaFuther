package com.groupX.ems.view_models;

import com.groupX.ems.models.enums.Role;

public class UserProfileViewModel {
    private long personId;
    private String fullName;
    private Role role;
    private String email;

    public UserProfileViewModel() {
    }

    public UserProfileViewModel(long personId, String fullName, Role role, String email) {
        this.personId = personId;
        this.fullName = fullName;
        this.role = role;
        this.email = email;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

