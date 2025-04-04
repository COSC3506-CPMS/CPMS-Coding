package com.cpms.cpms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);  // Launches the JavaFX application
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load FXML file for the main window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
        Parent root = loader.load();
        
        // Set up and show the main stage
        primaryStage.setTitle("CPMS Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
