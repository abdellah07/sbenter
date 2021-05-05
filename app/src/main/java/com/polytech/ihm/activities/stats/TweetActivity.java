package com.polytech.ihm.activities.stats;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.polytech.ihm.R;

public class TweetActivity extends AppCompatActivity {
    private ImageView back;
    private EditText tweetText;
    private Button ready;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        back = findViewById(R.id.back_navig);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ON BACK", "Go back!");
                onBackPressed();
            }
        });

        tweetText = findViewById(R.id.TweetTxt);

        ready = findViewById(R.id.done);
        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("In ready:", "Got some text?");
                if (tweetText.getText().length() != 0){
                    String tweetUrl = "https://twitter.com/intent/tweet?text=" + tweetText.getText();
                    Uri uri = Uri.parse(tweetUrl);
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            }
        });
    }
}