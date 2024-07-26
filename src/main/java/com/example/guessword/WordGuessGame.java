package com.example.guessword;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class WordGuessGame {

    private String wordToGuess;
    private Set<String> setList;

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

    // choose randomically a number based on List length
    public int selectRandomFromSet() {

        Random random = new Random();
        int randomNumber = random.nextInt(getSetList().size());

        return randomNumber;
    }

    public void playGame() {

        System.out.println(showUnderScoreInsteadOfWord());

        System.out.println("Guess the word, letter by letter!");
        Scanner scanner = new Scanner(System.in);
        char userInput = scanner.nextLine().charAt(0);

        scanner.close();
    }

    // This will show as many "_" as the word's size
    public String showUnderScoreInsteadOfWord() {


        System.out.println(getWordToGuess().length() + " letters: ");
        String worldPlaceHolder = "";
        for (int x = 0; x < getWordToGuess().length(); x++)

            worldPlaceHolder += " " + "_" + "";

        return worldPlaceHolder;
    }

    @Override
    public String toString() {
        return "Word to guess: " + getWordToGuess();
    }
}
