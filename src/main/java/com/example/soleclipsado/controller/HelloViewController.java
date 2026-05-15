package com.example.soleclipsado.controller;

import com.example.soleclipsado.model.HelloViewModel;
import com.example.soleclipsado.view.HelloView;
import com.example.soleclipsado.view.SolEclipsadoView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloViewController {

    private HelloViewModel helloViewModel;

    public HelloViewController() {

        this.helloViewModel = new HelloViewModel();
    }
    @FXML
    private Button PlayButton;


    @FXML
    private TextField SecretWordField;

    @FXML
    private Label WarningLabel;

    @FXML
    void OnActionPLayButton(ActionEvent event) throws IOException {

        String inputWord = SecretWordField.getText();
        String errorMessage = helloViewModel.inputErrorMessage(inputWord);

        if (!errorMessage.isEmpty()) {
            WarningLabel.setText(errorMessage);
            WarningLabel.setStyle("-fx-text-fill: red;");
        } else {
            SolEclipsadoView.getInstance().show();

            HelloView.getInstance().close();
        }




    }

}

