package com.cpms.cpms.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        primaryStage.setScene(new Scene(loader.load(), 400, 300));
        primaryStage.setTitle("User Login");
        primaryStage.setMaximized(true); 
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}