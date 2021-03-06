package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.network.FetchJokeAsyncTaskLoader;

import org.nowxd.joke_activity_library.JokeActivity;

public class MainActivity extends AppCompatActivity {

    private static final int LOADER_ID = 0;
    private LoaderManager.LoaderCallbacks<String> jokeLoaderCallbacks;
    private LoaderManager loaderManager;
    private ProgressBar jokeProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jokeProgressBar = (ProgressBar) findViewById(R.id.pb_joke_fetch);
        jokeProgressBar.setVisibility(View.INVISIBLE);
        defineLoader();
        loaderManager = getLoaderManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void defineLoader() {

        jokeLoaderCallbacks = new LoaderManager.LoaderCallbacks<String>() {
            @Override
            public android.content.Loader<String> onCreateLoader(int id, Bundle args) {

                jokeProgressBar.setVisibility(View.VISIBLE);

                FetchJokeAsyncTaskLoader loader = new FetchJokeAsyncTaskLoader(getApplicationContext());
                loader.forceLoad();

                return loader;

            }

            @Override
            public void onLoadFinished(Loader<String> loader, String joke) {

                jokeProgressBar.setVisibility(View.INVISIBLE);

                Class destination = JokeActivity.class;

                Intent intent = new Intent(getApplicationContext(), destination);
                intent.putExtra(getString(R.string.extra_joke_key), joke);

                startActivity(intent);

            }

            @Override
            public void onLoaderReset(Loader<String> loader) {

            }
        };

    }

    public void tellJoke(View view) {
        showJokeActivity();
    }

    public void showJokeActivity() {
        loaderManager.restartLoader(LOADER_ID, null, jokeLoaderCallbacks);
    }

}

