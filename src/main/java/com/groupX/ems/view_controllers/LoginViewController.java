package com.groupX.ems.view_controllers;

/**
 * @author 13
 */

import com.groupX.ems.controllers.AuthControllerAPI;

public class LoginViewController {

    private final AuthControllerAPI authControllerAPI;

    public LoginViewController(AuthControllerAPI authControllerAPI) {
        this.authControllerAPI = authControllerAPI;
    }

    public AuthControllerAPI.LoginResult handleLogin(String username, String password) {
        return authControllerAPI.login(username, password);
    }
}

