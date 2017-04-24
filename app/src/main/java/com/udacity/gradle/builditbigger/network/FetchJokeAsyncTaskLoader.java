package com.udacity.gradle.builditbigger.network;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import org.nowxd.backend_joke_lib.myApi.MyApi;

import java.io.IOException;

public class FetchJokeAsyncTaskLoader extends AsyncTaskLoader<String> {

    private static final boolean LOCAL_EMULATOR = false;

    private static MyApi myApiService = null;

    public FetchJokeAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public String loadInBackground() {

        if (myApiService == null) {  // Only do this once

            if (LOCAL_EMULATOR) initLocalApi();
            else initLiveApi();

        }

        try {
            return myApiService.getJokeBean().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    private void initLocalApi() {

        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                // options for running against local devappserver
                // - 10.0.2.2 is localhost's IP address in Android emulator
                // - turn off compression when running against local devappserver
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

        myApiService = builder.build();

    }

    private void initLiveApi() {

        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setRootUrl("https://joke-backend-165318.appspot.com/_ah/api/");

        myApiService = builder.build();

    }

}
