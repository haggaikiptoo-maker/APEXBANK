package com.mycompany.oopproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    
    public static String currentAccountNumber="";
    public static String currentUsername="";

     @Override
    public void start(Stage stage) throws IOException {
        DatabaseConnection.initializeDatabase();
       
        Parent root = loadFXML("login");
        scene = new Scene(root, 900, 600);
        
        java.net.URL cssResource = App.class.getResource("style.css");
        if (cssResource != null) {
            scene.getStylesheets().add(cssResource.toExternalForm());
        }
        stage.setScene(scene);
        stage.show();
    }
    

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
        
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void main(String[] args){
        launch();
    }
}
     