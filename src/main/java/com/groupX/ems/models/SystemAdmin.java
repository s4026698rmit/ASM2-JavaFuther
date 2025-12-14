package com.groupX.ems.models;

import com.groupX.ems.models.enums.Role;

import java.time.LocalDate;

public class SystemAdmin extends Person {
    private boolean superAdmin;

    public SystemAdmin() {
        super();
    }

    public SystemAdmin(long id, String fullName, LocalDate dob, String email, String phone, String username,
                       String passwordHash, Role role, boolean superAdmin) {
        super(id, fullName, dob, email, phone, username, passwordHash, role);
        this.superAdmin = superAdmin;
    }

    public boolean isSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        this.superAdmin = superAdmin;
    }
}

