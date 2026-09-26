module com.example.laba1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.laba1 to javafx.fxml;
    exports com.example.laba1;
    exports com.example.laba1.model;
    opens com.example.laba1.model to javafx.fxml;
}