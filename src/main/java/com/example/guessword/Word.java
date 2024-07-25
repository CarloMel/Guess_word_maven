package com.example.guessword;

public class Word {
    private String content;

    public Word(String content) throws Exception {

        setContent(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return getContent();
    }
}
