package com.cpms.cpms.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class ClientDashboardController {

    @FXML
    private Button viewProfileButton;
    @FXML
    private Button viewProjectsButton;
    @FXML
    private Button viewContractorsButton;
    @FXML
    private Button logoutButton;

    // This method handles the 'View Profile' button click
    @FXML
    private void handleViewProfile() {
        System.out.println("Navigating to Client Profile...");
        // Logic to navigate to client profile or show profile details
    }

    // This method handles the 'View Projects' button click
    @FXML
    private void handleViewProjects() {
        System.out.println("Navigating to Client Projects...");
        // Logic to navigate to projects or show client's projects
    }

    // This method handles the 'View Contractors' button click
    @FXML
    private void handleViewContractors() {
        System.out.println("Navigating to View Contractors...");
        // Logic to navigate to contractors or show the available contractors
    }

    // This method handles the 'Logout' button click
    @FXML
    private void handleLogout() {
        System.out.println("Logging out...");
        loadNewScreen("Login.fxml");
    }

    // Method to load a new screen (FXML file)
    private void loadNewScreen(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cpms/cpms/ui/" + fxmlFile));
            AnchorPane newScreen = loader.load();

            // Get the current stage (window)
            Stage stage = (Stage) viewProfileButton.getScene().getWindow();
            Scene scene = new Scene(newScreen);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Log errors for debugging
        }
    }
}
