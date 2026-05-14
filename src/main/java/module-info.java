module com.example.soleclipsado {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.soleclipsado to javafx.fxml;
    exports com.example.soleclipsado;
    exports com.example.soleclipsado.controller;
    opens com.example.soleclipsado.controller to javafx.fxml;
}