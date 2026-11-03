package com.mycompany.oopproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements javafx.fxml.Initializable {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;
    
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle rb){
        DatabaseConnection.initializeDatabase();
    }
    

    @FXML
    private void handleLogin() {
        String inputUser = usernameField.getText().trim();
        String inputPass = passwordField.getText().trim();

        if (inputUser.isEmpty() || inputPass.isEmpty()) {
            messageLabel.setText("Please fill out all fields.");
            return;
        }

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, inputUser);
            pstmt.setString(2, inputPass);
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Populate session data in App variables
                App.currentAccountNumber = rs.getString("user_id");
                App.currentUsername = rs.getString("username");
                
                // Redirect to main panel dashboard
                App.setRoot("primary");
            } else {
                messageLabel.setText("Invalid credentials.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            messageLabel.setText("An error occurred during authentication.");
        }
    }

    @FXML
    private void handleRegister() {
        String user = usernameField.getText().trim();
        String pass = passwordField.getText().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            messageLabel.setText("Please specify details to register.");
            return;
        }

        // Generate a random 6-digit bank account number
        String insertSQL = "INSERT INTO users(username,password, full_name,balance) VALUES(?, ?, ?, 500.0)"; // Free $500 balance bonus!

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            pstmt.setString(3, user);
            pstmt.executeUpdate();

            messageLabel.setStyle("-fx-text-fill: #2ebd59;");
            messageLabel.setText("Registered successfully! You can now log in");
            
        } catch (SQLException e) {
            messageLabel.setStyle("-fx-text-fill: #ff6b6b;");
            messageLabel.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}