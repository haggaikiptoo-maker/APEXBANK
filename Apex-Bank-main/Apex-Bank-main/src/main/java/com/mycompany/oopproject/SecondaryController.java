package com.mycompany.oopproject;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {
    
    
    public void handleDashboardView() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    public void handleTransferMoneyView() throws IOException{
        App.setRoot("transfer");
    }
    
    @FXML
    public void handleTransactionHistoryView() throws IOException {
        App.setRoot("history");
    }
    
    @FXML
    public void handleDepositView() throws IOException {
        App.setRoot("deposit");
    }
    
    @FXML
    public void handleWithdrawView() throws IOException {
        App.setRoot("withdraw");
    }

    @FXML
    public void handleLogOut() throws IOException{
        App.setRoot("primary");
    }
}
  
