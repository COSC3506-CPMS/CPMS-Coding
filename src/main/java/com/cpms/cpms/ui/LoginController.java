package com.cpms.cpms.ui;

import com.cpms.cpms.dao.UserDAO;
import com.cpms.cpms.entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private final UserDAO userDAO;

    public LoginController() {
        this.userDAO = new UserDAO(); // Initialize UserDAO
    }

    @FXML
    public void handleLogin() {
        String username = usernameField.getText(); // Get username input
        String password = passwordField.getText(); // Get password input

        // Fetch user from database using UserDAO
        User user = userDAO.getAllUsers().stream()
                .filter(u -> u.getUserName().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (user != null) {
            navigateToDashboard(user); // Redirect based on user role
        } else {
            errorLabel.setText("Invalid username or password!"); // Show error message
        }
    }

    private void navigateToDashboard(User user) {
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow(); // Get current stage
            FXMLLoader loader;

            // Determine which dashboard to load based on user role
            switch (user.getRole()) {
                case Admin:
                    loader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
                    break;
                case Contractor:
                    loader = new FXMLLoader(getClass().getResource("ContractorDashboard.fxml"));
                    break;
                case Client:
                    loader = new FXMLLoader(getClass().getResource("ClientDashboard.fxml"));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown role: " + user.getRole());
            }

            // Set the new scene to the stage
            stage.setScene(new Scene(loader.load(), 800, 600));
            stage.setTitle(user.getRole() + " Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Error loading dashboard!");
        }
    }
}