package com.example.soleclipsado.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MainGameController {



    private String secretWord;

    @FXML
    private ImageView eclipseImage;

    @FXML
    private Label inputErrorText;

    @FXML
    private Label missesDisplay;

    @FXML
    private HBox wordContainer;

    public void setSecretWord(String word) {
        this.secretWord = word;
    }

    public void createCharacterField() {

        }
    }

