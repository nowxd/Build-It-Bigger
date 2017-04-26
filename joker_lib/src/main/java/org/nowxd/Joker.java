package org.nowxd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Joker {

    private static final String jokeFileName = "/jokes.txt";
    private ArrayList<String> jokeList;

    private static final String ERROR_MESSAGE = "No jokes are available";

    private void readJokes() {

        try {

            jokeList = new ArrayList<>();

            InputStream in = getClass().getResourceAsStream(jokeFileName);
            BufferedReader input = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = input.readLine()) != null) {
                jokeList.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getJoke() {

        if (jokeList == null || jokeList.isEmpty()) return ERROR_MESSAGE;

        int len = jokeList.size();
        int randomIndex = (int) (Math.random() * len);

        return jokeList.get(randomIndex);

    }

    public Joker() {
        readJokes();
    }

}
