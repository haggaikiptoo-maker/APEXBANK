package com.mycompany.oopproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import java.io.IOException;

public class BankController {

    // If your main layout is an HBox, we can inject it to dynamically swap views
    @FXML
    private HBox mainRoot; 

    @FXML
    private void switchToDashboard(ActionEvent event) {
        System.out.println("Switching to Dashboard...");
        // loadView("Dashboard.fxml");
    }

    @FXML
    private void switchToDeposit(ActionEvent event) {
        System.out.println("Switching to Deposit...");
        // loadView("Deposit.fxml");
    }

    @FXML
    private void switchToWithdraw(ActionEvent event) {
        System.out.println("Switching to Withdraw...");
        // loadView("Withdraw.fxml");
    }

    @FXML
    private void switchToTransfer(ActionEvent event) {
        System.out.println("Switching to Transfer...");
        // loadView("Transfer.fxml");
    }

    @FXML
    private void switchToHistory(ActionEvent event) {
        System.out.println("Switching to History...");
        // loadView("History.fxml");
    }

    // Helper method to load a different sub-view into your layout if needed
    private void loadView(String fxmlFile) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlFile));
            // Logic to replace the right side area with 'view' goes here
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}