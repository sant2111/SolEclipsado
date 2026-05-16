package com.example.soleclipsado.controller;

import com.example.soleclipsado.model.SolEclipsadoModel;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainGameController {

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


    @FXML
    void onHintButton() {
        if (solEclipsadoModel.isWin() || solEclipsadoModel.isLoss()) return;

        String hint = solEclipsadoModel.getHintLetter();
        if (hint == null) {
            inputErrorText.setText("No quedan ayudas.");
            return;
        }
        solEclipsadoModel.guessLetter(hint);
        inputErrorText.setText("Ayuda: la letra '" + hint.toUpperCase() + "'");
        updateView();
        checkGameOver();
    }

    private void updateView() {
        // Revelar letras acertadas
        List<String> letters = solEclipsadoModel.getWordLetters();
        for (int i = 0; i < letters.size(); i++) {
            boolean revealed = solEclipsadoModel.getGuessedLetters().contains(letters.get(i));
            letterLabels.get(i).setText(revealed ? letters.get(i).toUpperCase() : "");

        }

        // Contador de errores
        missesDisplay.setText(solEclipsadoModel.getMissCount() + "/" + solEclipsadoModel.getMaxMisses());

        // Imagen del sol eclipsado según errores
        int idx = Math.min(solEclipsadoModel.getMissCount(), sunImages.length - 1);
        eclipseImage.setImage(new Image(getClass().getResourceAsStream(sunImages[idx])));

        // Ayudas restantes
        hintsLabel.setText("Ayudas: " + solEclipsadoModel.getHintsRemaining());
        hintButton.setDisable(solEclipsadoModel.getHintsRemaining() == 0);

        String used = solEclipsadoModel.getGuessedLetters().stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining("  "));
        usedLettersArea.setText(used);
    }

        private void checkGameOver() {
            if (solEclipsadoModel.isWin()) {
                inputErrorText.setText("¡Ganaste!");
                hintButton.setDisable(true);
            } else if (solEclipsadoModel.isLoss()) {
                inputErrorText.setText("¡Perdiste! Era: " + String.join("", solEclipsadoModel.getWordLetters()).toUpperCase());
                hintButton.setDisable(true);
                for (TextField letterfield : letterLabels) letterfield.setEditable(false);
            }
        }

    }


