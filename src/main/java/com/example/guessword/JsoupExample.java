package com.example.guessword;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupExample {
    public static void main(String[] args) {
        // Il link da cui estrarre il contenuto
        String url = "https://www.example.com";

        try {
            // Connessione al sito e parsing del documento HTML
            Document document = Jsoup.connect(url).get();

            // Estrazione del contenuto HTML come stringa
            String htmlContent = document.html();

            // Stampa del contenuto HTML
            System.out.println(htmlContent);

            // Salvataggio del contenuto in una variabile
            String extractedContent = htmlContent;

            // Utilizzo della variabile
            System.out.println("Contenuto estratto:\n" + extractedContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

