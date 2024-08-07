package com.example.guessword;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class WordGuessGame {

    private String wordToGuess;
    private Set<String> setList;
    private char userInput;
    private String userWord;

    public WordGuessGame(Set<String> setList) {

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

    public char getUserInput() {
        return userInput;
    }

    public void setUserInput(char userInput) {
        this.userInput = userInput;
    }

    public String getUserWord() {
        return userWord;
    }

    public void setUserWord(String userWord) {
        this.userWord = userWord;
    }

    // choose randomically a number based on List length
    public int selectRandomFromSet() {

        Random random = new Random();
        int randomNumber = random.nextInt(getSetList().size());

        return randomNumber;
    }

    public void playGame() {

        boolean victory = false;
        do {

            System.out.println(getWordToGuess().length() + " letters: ");
            System.out.println("Guess a letter!");
            System.out.println(getUserWord());
            Scanner scanner = new Scanner(System.in);
            setUserInput(scanner.nextLine().charAt(0));

            if (wordIsGuessed()) {

                System.out.println("You guessed the word!");
                System.out.println(getWordToGuess());
                victory = true;
                scanner.close();
            }

        } while (victory = false);
    }

    // // This will show as many "_" as the word's size
    public String underScorePlacer() {

        String worldPlaceHolder = "";
        for (int x = 0; x < getWordToGuess().length(); x++)

            if (!letterIsPresent(userInput)) {
                worldPlaceHolder += " " + "_" + " ";
            }

        return worldPlaceHolder;
    }

    public boolean letterIsPresent(char letter) {

        String word = getWordToGuess();
        for (int x = 0; x < word.length(); x++) {
            if (word.toCharArray()[x] == letter)
                return true;
        }
        return false;
    }

    public boolean wordIsGuessed() {
        if (getWordToGuess().equals(getUserWord()))
            return true;

        return false;
    }

    // DEVO AGGIUNGERE UN METODO CHE AGGIUNGA LE GIUSTE LETTERE
    // ALLE GIUSTE POSIZIONI DELLA PAROLA
    // E FARE IN MODO CHE underScorePlacer non sostituisca le lettere già scoperte

    @Override
    public String toString() {
        return "Word to guess: " + getWordToGuess();
    }
}
