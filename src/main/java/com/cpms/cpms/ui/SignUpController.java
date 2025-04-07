package com.cpms.cpms.ui;

import com.cpms.cpms.dao.UserDAO;
import com.cpms.cpms.entities.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class SignUpController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private ComboBox<User.Role> roleComboBox;

    @FXML
    private Label errorLabel;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    public void initialize() {
        // Populate the ComboBox with roles
        roleComboBox.setItems(FXCollections.observableArrayList(User.Role.values()));
    }

    @FXML
    public void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        User.Role role = roleComboBox.getValue(); // Get selected role

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || role == null) {
            errorLabel.setText("All fields are required!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            errorLabel.setText("Passwords do not match!");
            return;
        }

        // Set permissions based on role
        String permissions = switch (role) {
            case ADMIN -> "FULL_ACCESS";
            case CONTRACTOR -> "READ, WRITE";
            case CLIENT -> "READ-Only";
        };

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setRole(role);
        user.setPermissions(permissions); // Save permissions automatically

        boolean isRegistered = userDAO.addUser(user); // Save user to the database
        if (isRegistered) {
            errorLabel.setStyle("-fx-text-fill: green;");
            errorLabel.setText("Registration Successful!");
        } else {
            errorLabel.setStyle("-fx-text-fill: red;");
            errorLabel.setText("Username already exists or registration failed!");
        }
    }

    @FXML
    public void handleBackToLogin() {
        try {
            // Get the current stage (window)
            Stage stage = (Stage) usernameField.getScene().getWindow();
            
            // Load the Login page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cpms/cpms/ui/LoginPage.fxml"));
            Parent root = loader.load();
            
            // Set the new scene and maximize the window
            stage.setScene(new Scene(root, 1800, 800)); // Initial size (optional, will be overridden)
            stage.setMaximized(true); // Ensure the window is maximized
            stage.setTitle("Login Page");
            
            System.out.println("Navigating back to Login page...");
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Error navigating back to Login page!");
        }
    }
}
