package com.example.soleclipsado.model;

public class HelloViewModel implements InterfHelloView {

    private static final int MIN_WORD_CHARACTERS = 6;
    private static final int MAX_WORD_CHARACTERS = 12;
    private static final String validCHaracters = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZáéíóúÁÉÍÓÚüÜ";


    public int inputWordValidator(String secretWord) {

        if (secretWord.length() < MIN_WORD_CHARACTERS || secretWord.length() > MAX_WORD_CHARACTERS)
        {
        return 1;
        }


        for(int i = 0; i < secretWord.length(); i++) {
            if (Character.isWhitespace(secretWord.charAt(i))) {
                return 2;
            }
        }

        for(int i = 0; i < secretWord.length(); i++){

            char c = secretWord.charAt(i);
            if (validCHaracters.indexOf(c)== -1){

                return 3;
            }

        }

        return 0;
    }

    @Override
    public  String inputErrorMessage(String secretWord) {

        int errorType = inputWordValidator(secretWord);
        return switch (errorType) {
            case 1 ->
                    "La palabra debe contener entre" + " " + MIN_WORD_CHARACTERS + " " + "y" + " " + MAX_WORD_CHARACTERS + " " + "letras.";
            case 2 -> "La palabra no puede contener espacios en blanco";
            case 3 -> "La palabra solo puede contener letras del español";
            default -> "";
        };

    }

    }

