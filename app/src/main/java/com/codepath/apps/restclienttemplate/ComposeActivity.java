package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ComposeActivity extends AppCompatActivity {

    public static final int MAX_TWEET_LENGTH = 280;
    Button btnTweet;
    EditText etCompose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        // Initialize views
        btnTweet = findViewById(R.id.btnTweet);
        etCompose = findViewById(R.id.etCompose);

        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the composed tweet is valid
                String tweetContent = etCompose.getText().toString();
                if (tweetContent.isEmpty()) {
                    Toast.makeText(ComposeActivity.this, "Tweet cannot be empty",
                            Toast.LENGTH_LONG).show();
                } else if (tweetContent.length() > MAX_TWEET_LENGTH) {
                    Toast.makeText(ComposeActivity.this,
                            "Tweet cannot be longer than 140 chars", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ComposeActivity.this,
                            tweetContent, Toast.LENGTH_LONG).show();
                }
                // Make an API call to Twitter to publish the tweet

            }
        });
    }
}