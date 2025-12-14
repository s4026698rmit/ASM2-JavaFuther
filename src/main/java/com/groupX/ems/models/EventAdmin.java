package com.groupX.ems.models;

import com.groupX.ems.models.enums.Role;

import java.time.LocalDate;

public class EventAdmin extends Person {
    private String department;

    public EventAdmin() {
        super();
    }

    public EventAdmin(long id, String fullName, LocalDate dob, String email, String phone, String username,
                      String passwordHash, Role role, String department) {
        super(id, fullName, dob, email, phone, username, passwordHash, role);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

