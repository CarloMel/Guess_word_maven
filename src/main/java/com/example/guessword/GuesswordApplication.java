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

	public void test() throws Exception {

		// wb adds valid words to a list,
		// invalid words will be created as object but ignored (won't be added in list)
		// i'm adding words manually just as test
		// these words will be grabbed in a web page finally

		// LinkScanner ls = new
		// LinkScanner(https://it.wikipedia.org/wiki/Pagina_principale);
		WordGrabber wb = new WordGrabber();

		Word w0 = new Word("Hello");
		System.out.println(w0);
		wb.populateList(w0);

		Word w1 = new Word("Hello World");
		System.out.println(w1);
		wb.populateList(w1);

		Word w2 = new Word("Word2");
		System.out.println(w2);
		wb.populateList(w2);

		Word w3 = new Word("Wor'd");
		System.out.println(w3);
		wb.populateList(w3);

		Word w4 = new Word("Right");
		System.out.println(w4);
		wb.populateList(w4);

		// Creato un oggetto
		Word pippo = new Word("Pisello");
		// stampa quello all'interno di ()
		System.out.println(pippo);
		// popolo la lista
		wb.populateList(pippo);

		Word pippo1 = new Word("P1s3ll0");
		System.out.println(pippo1);
		wb.populateList(pippo1);

		System.out.println("List of words: " + wb);
	}

	public void start() {

		UrlHandler validLink = new UrlHandler("https://www.google.com/");
		UrlHandler invalidLink = new UrlHandler("pisnelo");
	}
}
