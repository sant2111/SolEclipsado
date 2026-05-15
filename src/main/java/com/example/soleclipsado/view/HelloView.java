package com.example.soleclipsado.view;

import com.example.soleclipsado.SolEclipsadoApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloView extends Stage {

    public HelloView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/soleclipsado/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setTitle("Sol Eclipsado");
        setScene(scene);
        setResizable(false);
        show();

    }

    public static HelloView getInstance() throws IOException {
        if (HelloViewHolder.INSTANCE == null) {
            HelloViewHolder.INSTANCE = new HelloView();

        }
        return HelloViewHolder.INSTANCE;
    }

    private static class HelloViewHolder {
        private static HelloView INSTANCE = null;
    }


 }