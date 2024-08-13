// takes link as input
// gives back 1 list of String

package com.example.guessword;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;

import java.io.IOException;
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

    // If Link is valid, this method will return one big block of unfiltered text
    public String getBlockFromURL() {

        String block = "";

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

        return block;
    }

    // Will filter 1 block of text and return a Set of playable words
    public Set<String> filterBlock() {

        Set<String> stringSet = new HashSet<>();

        String block = getBlockFromURL();
        String[] blockToArray = block.split("\\s+");
        for (String s : blockToArray) {

            String filteredWord = s.replaceAll("^[,.!?;:]+|[,.!?;:]+$", "||||");

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
