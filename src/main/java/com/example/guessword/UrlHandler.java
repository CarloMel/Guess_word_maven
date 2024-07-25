// takes link as input
// gives back a list of words

package com.example.guessword;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
// importing java.net
import java.net.URL;

public class UrlHandler {

    private String inputUrl;
    private List<Word> listFromUrl;

    public UrlHandler(String url) {

        setInputUrl(url);
        listFromUrl = new ArrayList<>();
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

        // if link is not valid you will get this
        if (!linkIsValid(inputUrl))
            System.out.println("Link is not valid");

    }

    public List<Word> getListFromUrl() {
        return listFromUrl;
    }

    public void setListFromUrl(List<Word> listFromUrl) {
        this.listFromUrl = listFromUrl;
    }

    public boolean linkIsValid(String link)  {

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
            //close connection
            connection.disconnect();

            if (connectionCode == 200) {
                System.out.println("Link is valid");
                return true;
            } else {
                System.out.println("Link is not valid");
                return false;
            }
        } catch (MalformedURLException e) {
            System.err.println("URL non valido: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("Errore di connessione o timeout per il link: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Si è verificato un errore imprevisto: " + e.getMessage());
            return false;
        }
    }
}

/*
 * catch (MalformedURLException e) {
    System.err.println("URL non valido: " + e.getMessage());
} catch (IOException e) {
    System.err.println("Errore di connessione o timeout per il link: " + e.getMessage());
} catch (Exception e) {
    System.err.println("Si è verificato un errore imprevisto: " + e.getMessage());
}
 */
