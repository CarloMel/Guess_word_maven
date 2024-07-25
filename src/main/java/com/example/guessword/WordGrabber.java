// This class filters words inside a List of Word

package com.example.guessword;

import java.util.ArrayList;
import java.util.List;

public class WordGrabber {
    private List<Word> wordList;

    public WordGrabber() {

        wordList = new ArrayList<>();
    }

    public List<Word> getWordList() {
        return wordList;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }

    public void populateList(Word word) {

        if (word.getContent() != null && isWordValid(word))
            getWordList().add(word);
    }

    public boolean isWordValid(Word word) {

        String s = word.getContent();
        if (s.length() < 4) {
            return false;
        }

        for (char c : word
        .getContent()
        .trim()
        .toUpperCase()
        .toCharArray()) {

            // && !Character.isWhitespace(c)
            if (!Character.isLetter(c)) 
                return false;
        }
        return true;
    }

    public List<String> createListFromBlock(String block) {

        List<String> list = new ArrayList<>();

        String[] sl = block.split(" ");

        for (String s : sl) {
            String filteredWord = s.replaceAll("^[,.!?;:]+|[,.!?;:]+$", "");
            if (!s.isEmpty()) {
                list.add(filteredWord);
            }
        }

        return list;
    }

    @Override
    public String toString() {

        return getWordList().toString();
    }
}
