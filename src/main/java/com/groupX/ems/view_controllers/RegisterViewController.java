package com.groupX.ems.view_controllers;

/**
 * @author 13
 */

import com.groupX.ems.controllers.AuthControllerAPI;

import java.time.LocalDate;

public class RegisterViewController {

    private final AuthControllerAPI authControllerAPI;

    public RegisterViewController(AuthControllerAPI authControllerAPI) {
        this.authControllerAPI = authControllerAPI;
    }

    public AuthControllerAPI.LoginResult handleRegister(String fullName,
                                                        LocalDate dob,
                                                        String email,
                                                        String phone,
                                                        String username,
                                                        String password,
                                                        String role) {
        return authControllerAPI.register(fullName, dob, email, phone, username, password, role);
    }
    
}

