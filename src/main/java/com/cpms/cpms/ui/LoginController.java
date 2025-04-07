package com.cpms.cpms.ui;

import java.util.List;

import com.cpms.cpms.config.CurrentUser;
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
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            errorLabel.setText("Username or password cannot be empty!");
            return;
        }

        User user = userDAO.getUserByUsernameAndPassword(username, password); // Query directly
        if (user != null) {
            CurrentUser.setLoggedInUser(user); // Set the logged-in user
            navigateToDashboard(user); // Navigate to the dashboard
        } else {
            errorLabel.setText("Invalid username or password!"); // Show error message
        }
    }
    
    //dashboard navigation
    private void navigateToDashboard(User user) {
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow(); // Get current stage
            FXMLLoader loader;

            // Determine which dashboard to load based on user role
            switch (user.getRole()) {
                case ADMIN:
                    loader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
                    break;
                case CONTRACTOR:
                    loader = new FXMLLoader(getClass().getResource("ContractorDashboard.fxml"));
                    break;
                case CLIENT:
                    loader = new FXMLLoader(getClass().getResource("ClientDashboard.fxml"));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown role: " + user.getRole());
            }
            // Set the new scene to the stage
            stage.setScene(new Scene(loader.load(), 800, 600));
            stage.setMaximized(true);
            stage.setTitle(user.getRole() + " Dashboard");
            

            
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Error loading dashboard!");
        }
    }
    
    //sign up handle
    @FXML
    public void handleSignUp() {
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cpms/cpms/ui/SignUp.fxml"));
            stage.setScene(new Scene(loader.load(), 400, 400));
            stage.setTitle("Sign Up");
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Error loading Sign-Up page!");
        }
    }
<<<<<<< HEAD
}

=======
}
>>>>>>> harman-finalWork
