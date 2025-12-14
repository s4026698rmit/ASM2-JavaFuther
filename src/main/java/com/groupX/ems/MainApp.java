package com.groupX.ems;

import com.groupX.ems.config.AppConfig;
import com.groupX.ems.config.DatabaseConnection;
import com.groupX.ems.utils.LocalStorageUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static Stage primaryStageRef;

    @Override
    public void init() {
        LocalStorageUtil.clearCurrentUser();
        DatabaseConnection.open();
        DatabaseConnection.executeSQLFromFile("/sql/setup.sql");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStageRef = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main_dashboard.fxml"));
        loader.setControllerFactory(clazz -> new AppConfig().createController(clazz));
        Parent root = loader.load();
        primaryStage.setTitle("Event Management System");
        primaryStage.setWidth(1400);
        primaryStage.setHeight(900);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() {
        LocalStorageUtil.clearCurrentUser();
        DatabaseConnection.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void switchScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(fxmlPath));
            loader.setControllerFactory(clazz -> new AppConfig().createController(clazz));
            Parent root = loader.load();
            if (primaryStageRef != null) {
                primaryStageRef.setScene(new Scene(root));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

