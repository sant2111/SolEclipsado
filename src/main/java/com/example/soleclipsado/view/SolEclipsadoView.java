package com.example.soleclipsado.view;

import com.example.soleclipsado.SolEclipsadoApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SolEclipsadoView extends Stage {

    public SolEclipsadoView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/soleclipsado/main-game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setTitle("Sol Eclipsado");
        setScene(scene);
        setResizable(false);
        show();

    }

    public static SolEclipsadoView getInstance() throws IOException {
        if (SolEclipsadoHolder.INSTANCE == null) {
            SolEclipsadoHolder.INSTANCE = new SolEclipsadoView();

        }
        return SolEclipsadoHolder.INSTANCE;
    }

    private static class SolEclipsadoHolder {
        private static SolEclipsadoView INSTANCE = null;
    }
}
