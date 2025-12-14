package com.groupX.ems.services;

/**
 * @author 13
 */

import com.groupX.ems.models.Person;
import com.groupX.ems.repositories.AuthRepository;
import com.groupX.ems.utils.LocalStorageUtil;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = Objects.requireNonNull(authRepository);
    }

    public Optional<Person> login(String username, String rawPassword) {
        Optional<Person> personOpt = authRepository.login(username, rawPassword);
        personOpt.ifPresent(person ->
                LocalStorageUtil.saveCurrentUser(person.getId(), person.getUsername(), person.getRole()));
        return personOpt;
    }

    public Person register(String fullName,
                           LocalDate dob,
                           String email,
                           String phone,
                           String username,
                           String rawPassword,
                           String role) {
        validateRegistration(fullName, email, username, rawPassword, role);
        Person person = authRepository.registerAccount(fullName, dob, email, phone, username, rawPassword, role);
        LocalStorageUtil.saveCurrentUser(person.getId(), person.getUsername(), person.getRole());
        return person;
    }

    public void logout() {
        LocalStorageUtil.clearCurrentUser();
    }

    private void validateRegistration(String fullName, String email, String username, String password, String role) {
        if (isBlank(fullName) || isBlank(email) || isBlank(username) || isBlank(password) || isBlank(role)) {
            throw new IllegalArgumentException("Required fields must not be blank");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

