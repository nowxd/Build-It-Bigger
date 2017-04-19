package org.nowxd.joke_activity_library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    TextView jokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        jokeTextView = (TextView) findViewById(R.id.tv_joke);

        Intent jokeIntent = getIntent();

        if (jokeIntent.hasExtra(getString(R.string.extra_joke_key))) {
            String joke = jokeIntent.getStringExtra(getString(R.string.extra_joke_key));
            jokeTextView.setText(joke);
        }
    }
}
