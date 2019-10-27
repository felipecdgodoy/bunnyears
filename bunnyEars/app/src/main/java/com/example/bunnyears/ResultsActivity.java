package com.example.bunnyears;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    public static MediaPlayer myMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myMediaPlayer = MediaPlayer.create(ResultsActivity.this, R.raw.song);
        myMediaPlayer.start();
        setContentView(R.layout.activity_results);
    }

}
