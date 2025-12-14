package com.groupX.ems.controllers;

/**
 * @author 13
 */

import com.groupX.ems.models.Person;
import com.groupX.ems.services.AuthService;

import java.time.LocalDate;
import java.util.Optional;

public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public Optional<Person> handleLogin(String username, String password) {
        return authService.login(username, password);
    }

    public Person handleRegister(String fullName,
                                 LocalDate dob,
                                 String email,
                                 String phone,
                                 String username,
                                 String password,
                                 String role) {
        return authService.register(fullName, dob, email, phone, username, password, role);
    }
}

