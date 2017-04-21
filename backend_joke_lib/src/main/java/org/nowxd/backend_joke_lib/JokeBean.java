package org.nowxd.backend_joke_lib;

import java.io.Serializable;

public class JokeBean implements Serializable {

    private String joke;

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

}
