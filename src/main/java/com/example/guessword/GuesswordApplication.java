/*
 * CODE IDEA:
 * Enter from scanner a link and the programm will catch
 * all the valid words (no numbers or special characters).
 * Then it randomly selects a word and you have to guess it.
 * based on how many tries you use for guessing the word,
 * you'll have a final rating.
 * You guess a word letter by letter
 */

package com.example.guessword;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

	// https://www.ebay.it/
	public void test() {

		Scanner scanner = new Scanner(System.in);
		boolean linkIsAccepted = false;
		boolean replayGame = false;

		String userURL;

		do {
			do {
				System.out.println("Insert a valid link to start the game: ");
				userURL = scanner.nextLine().trim();

				if (linkIsValid(userURL)) {
					System.out.println("Link accepted");
					linkIsAccepted = true;
				}
			} while (!linkIsAccepted);

			UrlHandler urlHandler = new UrlHandler(userURL);
			WordGuessGame wordGuessGame = new WordGuessGame(urlHandler.getSetFromUrl(), scanner);

			System.out.println("LIST: " + urlHandler.getSetFromUrl());
			System.out.println("LIST SIZE: " + urlHandler.getSetFromUrl().size());

			// generating a random number
			int randomNumber = wordGuessGame.selectRandomFromSet();
			// converting Set to List, so I can work with index
			List<String> setToList = new ArrayList<>(wordGuessGame.getSetList());
			// setting a random element of the list as word to guess
			wordGuessGame.setWordToGuess(setToList.get(randomNumber));
			// setting userWord length the same as WordToGuess
			wordGuessGame.setUserWord("_".repeat(wordGuessGame.getWordToGuess().length()));

			System.out.println("RANDOM NUMBER BASED ON LIST LENGTH: " + randomNumber);
			System.out.println("The " + randomNumber + "° SET ELEMENT IS: " + setToList.get(randomNumber));

			wordGuessGame.playGame();
			boolean repeat = false;

			do {
				System.out.println("Do you want to try again with a new game? y/n");
				char answer = scanner.nextLine().toUpperCase().charAt(0);

				if (answer == 'y') {
					replayGame = true;
					repeat = false;
				} else if (answer == 'n') {
					replayGame = false;
					repeat = false;
				} else {
					repeat = true;
				}
			} while (repeat);

		} while (replayGame);
		System.out.println("Thank you for playing!");
		scanner.close();
		System.exit(0);
	}

	public void start() {

		Scanner scan = new Scanner(System.in);
		boolean linkIsAccepted = false;
		String userURL;

		do {
			System.out.println("Insert a valid link to start the game: ");
			userURL = scan.nextLine().trim();

			if (linkIsValid(userURL)) {
				System.out.println("Link accepted");
				linkIsAccepted = true;
			}
		} while (!linkIsAccepted);

		UrlHandler urlHandler = new UrlHandler(userURL);

		WordGuessGame wordGuessManager = new WordGuessGame(urlHandler.getSetFromUrl(), scan);

		// generating a random number
		int randomNumber = wordGuessManager.selectRandomFromSet();
		// converting Set to List, so I can work with index
		List<String> setToList = new ArrayList<>(wordGuessManager.getSetList());
		// setting a random element of the list as word to guess
		wordGuessManager.setWordToGuess(setToList.get(randomNumber));
		// setting userWord length the same as WordToGuess
		wordGuessManager.setUserWord("_".repeat(wordGuessManager.getWordToGuess().length()));

		wordGuessManager.playGame();
		System.out.println("Thank you for playing!");
		scan.close();
		System.exit(0);
	}

	public boolean linkIsValid(String link) {

		try {

			// saves inputUrl as URL object
			URL url = new URL(link);
			// opens a HTTP connection to the link
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// set get as method of request
			connection.setRequestMethod("GET");
			// set a 5 second timer for the connection
			connection.setConnectTimeout(5000);
			// create a variable that will have a code of response for connection
			int connectionCode = connection.getResponseCode();
			// close connection
			connection.disconnect();

			if (connectionCode == 200) {
				return true;
			} else {
				return false;
			}
		} catch (MalformedURLException e) {
			System.err.println("Unvalid URL: " + e.getMessage());
			return false;
		} catch (IOException e) {
			System.err.println("Connection error or timeout link: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.err.println("Unexpected error: " + e.getMessage());
			return false;
		}
	}
}
