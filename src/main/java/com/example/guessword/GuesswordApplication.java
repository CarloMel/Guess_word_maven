/*
 * CODE IDEA:
 * Enter (by scanner or by code) a link and the programm will catch
 * all the valid words (no numbers or strange strings).
 * Then it randomly selects a word and you have to guess it.
 * based on how many tries you use for guessing the word,
 * you'll have a final rating.
 * You guess a word letter by letter
 */

package com.example.guessword;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuesswordApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GuesswordApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// start();
		test();
	}

	public void test() {

		UrlHandler urlHandler = new UrlHandler("https://www.ebay.it/");
		WordGuessGame wordGuessGame = new WordGuessGame(urlHandler.getSetFromUrl());

		System.out.println("LIST: " + urlHandler.getSetFromUrl());
		System.out.println("LIST SIZE: " + urlHandler.getSetFromUrl().size());

		// generating a random number
		int randomNumber =   wordGuessGame.selectRandomFromSet();
		// converting Set to List, so I can work with index
		List<String> setToList = new ArrayList<>(wordGuessGame.getSetList());
		// setting a random element of the list as word to guess
		wordGuessGame.setWordToGuess(setToList.get(randomNumber));
		// setting userWord length the same as WordToGuess
		wordGuessGame.setUserWord("_".repeat(wordGuessGame.getWordToGuess().length()));

		System.out.println("RANDOM NUMBER BASED ON LIST LENGTH: " + randomNumber);
		System.out.println("The " + randomNumber + "° SET ELEMENT IS: " + setToList.get(randomNumber));

		wordGuessGame.playGame();
	}

	public void start() {

		UrlHandler urlHandler = new UrlHandler("https://terraria.wiki.gg/wiki/Golden_Shower");
		WordGuessGame wordGuessManager = new WordGuessGame(urlHandler.getSetFromUrl());

		// generating a random number
		int randomNumber =   wordGuessManager.selectRandomFromSet();
		// converting Set to List, so I can work with index
		List<String> setToList = new ArrayList<>(wordGuessManager.getSetList());
		// setting a random element of the list as word to guess
		wordGuessManager.setWordToGuess(setToList.get(randomNumber));
		// setting userWord length the same as WordToGuess
		wordGuessManager.setUserWord("_".repeat(wordGuessManager.getWordToGuess().length()));

		wordGuessManager.playGame();
	}
}
