package com.groupX.ems.config;

/**
 * @author 13
 */

import com.groupX.ems.controllers.AuthController;
import com.groupX.ems.controllers.AuthControllerAPI;
import com.groupX.ems.services.AuthService;
import com.groupX.ems.services.EventService;
import com.groupX.ems.services.EventServiceImpl;
import com.groupX.ems.services.PresenterService;
import com.groupX.ems.services.PresenterServiceImpl;
import com.groupX.ems.view_controllers.AttendeeDashboardController;
import com.groupX.ems.view_controllers.EventAdminDashboardController;
import com.groupX.ems.view_controllers.LoginViewController;
import com.groupX.ems.view_controllers.NotificationViewController;
import com.groupX.ems.view_controllers.PresenterDashboardController;
import com.groupX.ems.view_controllers.RegisterViewController;
import com.groupX.ems.view_controllers.ReportViewController;
import com.groupX.ems.view_controllers.SystemAdminDashboardController;
import com.groupX.ems.view_controllers.MainDashboardController;

public class AppConfig {

    private final AuthService authService = new AuthService(new com.groupX.ems.repositories.AuthRepository());
    private final AuthController authController = new AuthController(authService);
    private final AuthControllerAPI authControllerAPI = new AuthControllerAPI(authController);
    private final EventService eventService = new EventServiceImpl();
    private final PresenterService presenterService = new PresenterServiceImpl(new com.groupX.ems.repositories.PresenterRepository() {
        @Override
        public java.util.Optional<com.groupX.ems.models.Presenter> findById(long id) { return java.util.Optional.empty(); }
        @Override
        public java.util.List<com.groupX.ems.models.Presenter> findAll() { return java.util.List.of(); }
        @Override
        public com.groupX.ems.models.Presenter save(com.groupX.ems.models.Presenter presenter) { return presenter; }
        @Override
        public void delete(long id) { }
    });

    public Object createController(Class<?> clazz) {
        if (clazz == LoginViewController.class) {
            return new LoginViewController(authControllerAPI);
        }
        if (clazz == RegisterViewController.class) {
            return new RegisterViewController(authControllerAPI);
        }

        if (clazz == AttendeeDashboardController.class) {
            return new AttendeeDashboardController();
        }
        if (clazz == PresenterDashboardController.class) {
            return new PresenterDashboardController();
        }
        if (clazz == EventAdminDashboardController.class) {
            return new EventAdminDashboardController();
        }
        if (clazz == SystemAdminDashboardController.class) {
            return new SystemAdminDashboardController();
        }
        if (clazz == ReportViewController.class) {
            return new ReportViewController();
        }
        if (clazz == NotificationViewController.class) {
            return new NotificationViewController();
        }
        if (clazz == MainDashboardController.class) {
            return new MainDashboardController(eventService, presenterService);
        }
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("Unable to create controller for: " + clazz, e);
        }
    }
}

