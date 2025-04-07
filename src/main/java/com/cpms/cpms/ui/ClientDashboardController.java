package com.cpms.cpms.ui;

<<<<<<< HEAD
import com.cpms.cpms.dao.ServiceRequestDAO;
import com.cpms.cpms.entities.ServiceRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ClientDashboardController {

    @FXML private Label welcomeLabel;
    @FXML private TextArea descriptionField;
    @FXML private DatePicker requestDatePicker;
    @FXML private Label statusLabel; // Success message label
    @FXML private Button btnExit;

    private final ServiceRequestDAO serviceRequestDAO = new ServiceRequestDAO();

    @FXML
    public void handleSubmitRequest() {
        String description = descriptionField.getText();
        LocalDate date = requestDatePicker.getValue();

        if (description.isEmpty() || date == null) {
            statusLabel.setText("Please fill in all fields.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Combine the selected date with the current time to create a timestamp
        Timestamp timestamp = Timestamp.valueOf(date.atTime(12, 0, 0)); // Example: Default time is 12:00:00

        ServiceRequest request = new ServiceRequest();
        request.setRequestDetails(description);
        request.setRequestDate(timestamp);

        serviceRequestDAO.addServiceRequest(request);

        statusLabel.setText("Service Request Submitted Successfully!");
        statusLabel.setStyle("-fx-text-fill: green;");
        clearForm();
    }

    private void clearForm() {
        descriptionField.clear();
        requestDatePicker.setValue(null);
    }
    //to exit from dashboard
    @FXML
    private void handleExit() {
        try {
            // Load the Login page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cpms/cpms/ui/LoginPage.fxml"));
            Parent root = loader.load();

            // Get the current stage (window) and set the new scene
            Stage stage = (Stage) btnExit.getScene().getWindow(); 
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            //stage.setWidth(1700); // Example of overriding width
            //stage.setHeight(900); // Example of overriding height
            stage.setTitle("Login Page");

            // Print confirmation
            System.out.println("Navigating back to Login page...");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the Login page.");
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
=======
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
>>>>>>> komal-finalWork
