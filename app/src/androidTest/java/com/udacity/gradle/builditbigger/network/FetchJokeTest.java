package com.udacity.gradle.builditbigger.network;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.LoaderTestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FetchJokeTest extends LoaderTestCase {

    private Context context;

    @Before
    public void setup() {
        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void testFetchJoke() {

        FetchJokeAsyncTaskLoader loader = new FetchJokeAsyncTaskLoader(context);
        loader.forceLoad();

        String joke = getLoaderResultSynchronously(loader);
        System.out.println("Joke: " + joke);
        Assert.assertTrue(!joke.isEmpty());

    }

}
