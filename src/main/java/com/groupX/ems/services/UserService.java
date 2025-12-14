package com.groupX.ems.services;

import com.groupX.ems.models.Person;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<Person> findById(long id);
    Optional<Person> findByUsername(String username);
    List<Person> findAll();
    Person save(Person person);
    void delete(long id);
}

