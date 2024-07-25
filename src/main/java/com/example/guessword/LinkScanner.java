package com.example.guessword;

// import org.jsoup.Jsoup;
// import org.jsoup.nodes.Document;

public class LinkScanner {
    
    private String url;

    public LinkScanner(String url) {
        
        setUrl(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isLinkValid(String link) {
        // se il sito is connette, return true
        // altrimenti
        return false;
    }

    @Override
    public String toString() {
        return getUrl();
    }

     // Connessione al sito e parsing del documento HTML
    //  Document document = Jsoup.connect(getUrl()).get();
}
