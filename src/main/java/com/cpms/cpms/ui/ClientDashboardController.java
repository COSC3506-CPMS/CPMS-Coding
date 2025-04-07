package com.cpms.cpms.ui;

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