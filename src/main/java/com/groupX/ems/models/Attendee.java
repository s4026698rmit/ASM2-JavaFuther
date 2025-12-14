package com.groupX.ems.models;

import com.groupX.ems.models.enums.Role;

import java.time.LocalDate;

public class Attendee extends Person {

    public Attendee() {
        super();
    }

    public Attendee(long id, String fullName, LocalDate dob, String email, String phone, String username,
                    String passwordHash, Role role) {
        super(id, fullName, dob, email, phone, username, passwordHash, role);
    }
}

