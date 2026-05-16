package com.example.soleclipsado.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MainGameController {

<<<<<<< Updated upstream

=======
    @FXML private ImageView eclipseImage;
    @FXML private Label     missesDisplay;
    @FXML private Label     inputErrorText;
    @FXML private HBox      wordContainer;
    @FXML private Label     hintsLabel;
    @FXML private Button    hintButton;
    @FXML private TextArea usedLettersArea;

    private SolEclipsadoModel solEclipsadoModel;
    private final List<TextField> letterLabels = new ArrayList<>();
    private TextField activeField;

    private final String[] sunImages = {
            "/Images/sun_0.png",
            "/Images/sun_1.png",
            "/Images/sun_2.png",
            "/Images/sun_4.png",
            "/Images/sun_5.png",
            "/Images/sun_6.png"
    };

    public void setSecretWord(String word) {
        this.solEclipsadoModel = new SolEclipsadoModel(word);
        buildWordDisplay();
        updateView();
    }

    private void buildWordDisplay() {
        wordContainer.getChildren().clear();
        letterLabels.clear();

        for (String ignored : solEclipsadoModel.getWordLetters()) {
            TextField letterField = new TextField();
            letterField.setPrefWidth(50);
            letterField.setPrefHeight(50);
            letterField.setMaxWidth(50);
            letterField.setAlignment(Pos.CENTER);
            letterField.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
            letterField.setEditable(false);

            // Al hacer clic en la casilla se activa el input
            letterField.setOnMouseClicked(e -> {
                if (!solEclipsadoModel.isWin() && !solEclipsadoModel.isLoss()) {
                    for (TextField other : letterLabels) {
                        other.setEditable(false);
                    }
                    activeField = letterField;
                    letterField.setEditable(true);
                    letterField.requestFocus();
                }
            });

            // Al escribir una letra se procesa
            letterField.setOnKeyTyped(e -> {
                if (!letterField.isEditable()) return;
                String letter = e.getCharacter().toLowerCase();
                letterField.setEditable(false);
                letterField.clear();
                processGuess(letter);
            });

            letterLabels.add(letterField);
            wordContainer.getChildren().add(letterField);
        }
    }

    private void processGuess(String letter) {
        if (solEclipsadoModel.isWin() || solEclipsadoModel.isLoss()) return;
        if (!letter.matches("[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ]")) return;
        if (solEclipsadoModel.getGuessedLetters().contains(letter)) {
            inputErrorText.setText("Ya usaste esa letra.");
            return;
        }
        boolean hit = solEclipsadoModel.guessLetter(letter);
        inputErrorText.setText(hit ? "¡Bien!" : "Letra incorrecta.");
        updateView();
        checkGameOver();
    }
>>>>>>> Stashed changes

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

