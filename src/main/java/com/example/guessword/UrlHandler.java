// takes link as input
// gives back 1 list of String

package com.example.guessword;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class UrlHandler {

    private String inputUrl;
    private Set<String> setFromUrl;

    public UrlHandler(String url) {

        setInputUrl(url);
        setFromUrl = filterBlock();
    }

    public String getInputUrl() {
        return inputUrl;
    }

    public void setInputUrl(String inputUrl) {

        // if link is null you will get this
        if (inputUrl == null) {
            System.out.println("You didn't write a link");
        } else {
            // if link works
            this.inputUrl = inputUrl;
        }
    }

    public Set<String> getSetFromUrl() {
        return setFromUrl;
    }

    public void setSetFromUrl(Set<String> setFromUrl) {
        this.setFromUrl = setFromUrl;
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

    // If Link is valid, this method will return one big block of unfiltered text
    public String getBlockFromURL() {

        String block = "";

        if (linkIsValid(getInputUrl())) {
            try {
                // connecting to page
                // loading page's content into a Document object
                Document document = Jsoup.connect(getInputUrl()).get();
                // selecting document's <body>
                Element body = document.body();

                // if I wanted to select something inside body like <a> for example
                // Elements e = body.select("a");

                // add content of body inside a variable
                block = body.text();
            } catch (IOException body) {
                System.out.println("Error: " + body.getMessage());
            }
        }
        return block;
    }

    // Will filter 1 block of text and return a Set of playable words
    public Set<String> filterBlock() {

        Set<String> stringSet = new HashSet<>();

        String block = getBlockFromURL();
        String[] blockToArray = block.split("\\s+");
        for (String s : blockToArray) {

            String filteredWord = s.replaceAll("^[,.!?;:]+|[,.!?;:]+$", "");

            // if word is not empty, it's valid
            if (!filteredWord.isEmpty()
                    && isWordValid(filteredWord))
                stringSet.add(filteredWord.toUpperCase());
        }

        // System.out.println("stringSet: " + stringSet);
        return stringSet;
    }

    // Gets 1 String return true if it's a playable word
    public boolean isWordValid(String word) {

        String s = word;
        if (s.length() < 4) {
            return false;
        }

        for (char c : word
                .trim()
                .toUpperCase()
                .toCharArray()) {

            if (c < 'A' || c > 'Z')
                return false;
        }
        return true;
    }
}
