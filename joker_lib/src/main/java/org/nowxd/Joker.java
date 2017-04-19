package org.nowxd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Joker {

    private static final String jokeFileName = "/jokes.txt";
    private ArrayList<String> jokeList;
    private int jokeIndex;

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

        String joke = jokeList.get(jokeIndex++);

        if (jokeIndex >= jokeList.size()) {
            jokeIndex = 0;
            Collections.shuffle(jokeList);
        }

        return joke;

    }

    public Joker() {
        jokeIndex = 0;
        readJokes();
    }

}
