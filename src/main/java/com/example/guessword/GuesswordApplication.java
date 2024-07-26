/*
 * CODE IDEA:
 * Enter (by scanner or by code) a link and the programm will catch
 * all the valid words (no numbers or strange strings).
 * Then it randomly selects a word and you have to guess it within
 * N guesses (they will increase according to the length of the word)
 * You guess a word letter by letter
 */

package com.example.guessword;

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

		start();
	}

	public void start() {

		UrlHandler urlHandler = new UrlHandler("https://terraria.wiki.gg/wiki/Golden_Shower");
		System.out.println("LIST: " + urlHandler.getListFromUrl());
		System.out.println("LIST SIZE: " + urlHandler.getListFromUrl().size());
	}
}
