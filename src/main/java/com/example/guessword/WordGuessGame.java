package com.example.guessword;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class WordGuessGame {

    private String wordToGuess;
    private Set<String> setList;
    private char userInput;
    private String userWord = "";

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
        Scanner scanner = new Scanner(System.in);

        do {

            System.out.println(getWordToGuess().length() + " letters: ");
            System.out.println("Guess a letter!");
            System.out.println("Letters guessed so far: " + getUserWord());
            setUserInput(scanner.nextLine().charAt(0));
            letterPlacer(getUserInput());

            if (wordIsGuessed()) {

                System.out.println("You guessed the word!");
                System.out.println(getWordToGuess());
                victory = true;

            }

        } while (!victory);

        scanner.close();
    }

    public void letterPlacer(char letter) {

        char[] wordToGuessArray = getWordToGuess().toCharArray();
        StringBuilder userWord = new StringBuilder(getUserWord());

        for (int x = 0; x < wordToGuessArray.length; x++) {

            if (wordToGuessArray[x] == Character.toUpperCase(letter))
                userWord.setCharAt(x, Character.toUpperCase(letter));
        }

        setUserWord(userWord.toString());
    }

    public boolean wordIsGuessed() {
        if (getWordToGuess().equals(getUserWord()))
            return true;

        return false;
    }

    @Override
    public String toString() {
        return "Word to guess: " + getWordToGuess();
    }
}
