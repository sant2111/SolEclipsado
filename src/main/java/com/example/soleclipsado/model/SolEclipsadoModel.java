package com.example.soleclipsado.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolEclipsadoModel implements InterfMainGame {

    private static final int MAX_MISSES = 5;
    private static final int MAX_HINTS  = 3;

    private final List<String> wordLetters;
    private final Set<String>  guessedLetters = new HashSet<>();
    private int missCount = 0;
    private int hintsUsed = 0;

    public SolEclipsadoModel(String secretWord) {
        this.wordLetters = Arrays.asList(secretWord.toLowerCase().split(""));
    }

    public boolean guessLetter(String letter) {
        letter = letter.toLowerCase();
        guessedLetters.add(letter);
        boolean hit = wordLetters.contains(letter);
        if (!hit) missCount++;
        return hit;
    }

    // Devuelve una letra no adivinada aún, o null si no quedan ayudas
    public String getHintLetter() {
        if (hintsUsed >= MAX_HINTS) return null;
        for (String letter : wordLetters) {
            if (!guessedLetters.contains(letter)) {
                hintsUsed++;
                return letter;
            }
        }
        return null;
    }

    public boolean isWin() {
        return new HashSet<>(wordLetters).stream().allMatch(guessedLetters::contains);
    }

    public boolean isLoss()                  { return missCount >= MAX_MISSES; }
    public int     getMissCount()             { return missCount; }
    public int     getMaxMisses()             { return MAX_MISSES; }
    public int     getHintsRemaining()        { return MAX_HINTS - hintsUsed; }
    public List<String> getWordLetters()      { return wordLetters; }
    public Set<String>  getGuessedLetters()   { return guessedLetters; }

    @Override
    public void gameSecretWord(String secretWord) {}
}
