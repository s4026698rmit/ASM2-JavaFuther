package com.groupX.ems.controllers;

/**
 * @author 13
 */

import com.groupX.ems.models.Person;
import com.groupX.ems.models.enums.Role;

import java.time.LocalDate;
import java.util.Optional;

public class AuthControllerAPI {

    private final AuthController authController;

    public AuthControllerAPI(AuthController authController) {
        this.authController = authController;
    }

    public LoginResult login(String username, String password) {
        Optional<Person> personOpt = authController.handleLogin(username, password);
        if (personOpt.isPresent()) {
            Person person = personOpt.get();
            return new LoginResult(true, "Login successful", person, person.getRole());
        }
        return new LoginResult(false, "Invalid username or password", null, null);
    }

    public LoginResult register(String fullName,
                                LocalDate dob,
                                String email,
                                String phone,
                                String username,
                                String password,
                                String role) {
        try {
            Person person = authController.handleRegister(fullName, dob, email, phone, username, password, role);
            return new LoginResult(true, "Registration successful", person, person.getRole());
        } catch (Exception e) {
            return new LoginResult(false, e.getMessage(), null, null);
        }
    }

    public record LoginResult(boolean success, String message, Person person, Role role) {
    }
}

