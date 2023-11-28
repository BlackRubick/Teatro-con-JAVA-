module com.example.teatro2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.teatro2 to javafx.fxml;
    exports com.example.teatro2;
}