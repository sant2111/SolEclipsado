package com.example.soleclipsado;

import com.example.soleclipsado.view.HelloView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SolEclipsadoApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        HelloView helloView = HelloView.getInstance();
        helloView.show();
    }
}
