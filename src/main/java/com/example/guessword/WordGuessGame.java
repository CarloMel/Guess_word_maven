package com.example.guessword;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class WordGuessGame {

    private String wordToGuess;
    private Set<String> setList;
    private char userInput;
    private String userWord = "";
    private int tryCounter = 0;
    private Set<Character> lettersUsed;

    public WordGuessGame(Set<String> setList) {

        this.setList = setList;
        lettersUsed = new TreeSet<>();
    }

    public Set<Character> getLettersUsed() {
        return lettersUsed;
    }

    public void setLettersUsed(Set<Character> lettersUsed) {
        this.lettersUsed = lettersUsed;
    }

    public int getTryCounter() {
        return tryCounter;
    }

    public void setTryCounter(int tryCounter) {
        this.tryCounter = tryCounter;
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
        try {
            do {

                System.out.println(getWordToGuess().length() + " letters: ");
                System.out.println("Guess a letter!");
                System.out.println("Letters guessed so far: " + getUserWord());
                System.out.println(getQwerty());

                String userInputString = scanner.nextLine();

                if (userInputString.matches(".*\\d.*")) {
                    System.out.println("Number don't work. You must enter a letter");
                }

                if (userInputString.isEmpty()) {
                    System.out.println("You must enter a letter");
                    continue;
                } else {
                    setUserInput(userInputString.charAt(0));
                    letterPlacer(getUserInput());
                }

                if (wordIsGuessed()) {

                    System.out.println("You guessed the word!");
                    System.out.println(getWordToGuess());
                    System.out.println("It took you: " + getTryCounter() + " tries.");
                    System.out.println("Your score is: " + getScore());
                    victory = true;

                }

                setTryCounter(tryCounter + 1);
            } while (!victory);
        } catch (Exception e) {
            System.out.println("You have to write a letter");
        }

        scanner.close();
    }

    public void letterPlacer(char letter) {

        char[] wordToGuessArray = getWordToGuess().toCharArray();
        StringBuilder userWord = new StringBuilder(getUserWord());
        char upperCaseLetter = Character.toUpperCase(letter);

        for (int x = 0; x < wordToGuessArray.length; x++) {

            if (wordToGuessArray[x] == upperCaseLetter)
                userWord.setCharAt(x, upperCaseLetter);
        }

        if (!Character.isDigit(letter)) {
            getLettersUsed().add(upperCaseLetter);
        }

        setUserWord(userWord.toString());
    }

    public boolean wordIsGuessed() {
        if (getWordToGuess().equals(getUserWord()))
            return true;

        return false;
    }

    public String getScore() {

        int tries = getTryCounter();
        int wordLength = getWordToGuess().length();

        if (tries <= wordLength) {
            return "S++! PERFECT JOB!";
        }

        if (tries <= (wordLength * 2)) {
            return "S";
        }

        if (tries <= (wordLength * 3 - (wordLength / 2))) {
            return "A";
        }

        if (tries <= (wordLength * 4 - (wordLength / 3))) {
            return "B";
        }

        if (tries <= (wordLength * 5 - (wordLength / 4))) {
            return "C";
        }

        if (tries <= (wordLength * 6 - (wordLength / 5))) {
            return "D";
        }

        if (tries <= (wordLength * 7 - (wordLength / 6))) {
            return "E";
        }

        return "F";
    }

    public String getQwerty() {
        char[] qwertyOrder = {
                'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P',
                'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L',
                'Z', 'X', 'C', 'V', 'B', 'N', 'M'
        };

        StringBuilder display = new StringBuilder();

        // Add the first row of the keyboard (QWERTYUIOP)
        for (int i = 0; i < 10; i++) {
            // Append the letter if it is not used, otherwise append a space
            display.append(getLettersUsed().contains(qwertyOrder[i]) ? ' ' : qwertyOrder[i])
                    // Append a space after each letter
                    .append(' ');
        }
        // Add a newline after the first row
        display.append('\n');

        // Add the second row of the keyboard (ASDFGHJKL)
        for (int i = 10; i < 19; i++) {
            display.append(getLettersUsed().contains(qwertyOrder[i]) ? ' ' : qwertyOrder[i])
                    .append(' ');
        }
        display.append('\n');

        // Add the third row of the keyboard (ZXCVBNM)
        for (int i = 19; i < qwertyOrder.length; i++) {
            display.append(getLettersUsed().contains(qwertyOrder[i]) ? ' ' : qwertyOrder[i])
                    .append(' ');
        }

        // Return the formatted string representing the QWERTY keyboard
        return display.toString();
    }

    @Override
    public String toString() {
        return "Word to guess: " + getWordToGuess();
    }
}
