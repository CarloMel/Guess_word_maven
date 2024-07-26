package com.example.guessword;

import java.util.Random;
import java.util.Set;

public class WordGuessGame {

    private String wordToGuess;
    private Set<String> setList;

    public WordGuessGame (Set<String> setList) {

        this.setList = setList;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public void setWordToGuess(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public Set<String> getSetList() {
        return setList;
    }

    public void setSetList(Set<String> setList) {
        this.setList = setList;
    }
    
    // choose randomically a number based on List length
    public int selectRandomFromSet() {

        Random random = new Random();
        int randomNumber = random.nextInt(getSetList().size());

        return randomNumber;
    }
    
    @Override
    public String toString() {
        return "Word to guess: " + getWordToGuess();
    }
}
