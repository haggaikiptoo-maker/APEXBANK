package com.mycompany.oopproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable {

    @FXML private Label welcomeLabel;
    @FXML private Label balanceLabel;
    @FXML private TextField amountField;
    @FXML private Label depositMessageLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUserData();
    }

    private void loadUserData() {
        if (App.currentUsername == null || App.currentUsername.isEmpty()) {
            welcomeLabel.setText("Welcome Back!");
            balanceLabel.setText("$0.00");
            return;
        }

        welcomeLabel.setText("Welcome Back, " + App.currentUsername + "!");

        String query = "SELECT balance FROM users WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, App.currentAccountNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");
                balanceLabel.setText(String.format("$%.2f", currentBalance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            balanceLabel.setText("Error loading balance");
        }
    }

    @FXML
    private void switchToDeposit() throws IOException {
        App.setRoot("deposit");
    }

    @FXML
    private void switchToWithdraw() throws IOException {
        App.setRoot("withdraw");
    }

    @FXML
    private void switchToTransfer() throws IOException {
        App.setRoot("transfer");
    }

    @FXML
    private void switchToHistory() throws IOException {
        App.setRoot("history");
    }
    
     

    // --- PASTE THE DEPOSIT METHODS HERE ---
    @FXML
    private void handleDepositSubmit() {
        // ... deposit code ...
    }

    @FXML
    private void handleDashboardView() throws IOException {
        App.setRoot("primary");
    }
}  
