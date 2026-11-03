module com.mycompany.oopproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.oopproject to javafx.fxml;
    exports com.mycompany.oopproject;
}