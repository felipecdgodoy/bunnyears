package com.example.bunnyears;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.graphics.Path;


public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    public static MediaPlayer myMediaPlayer;
    ImageView tealBunnyImageView;
    ObjectAnimator tealBunnyObjectAnimator;
    Path bunnyPath;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        myMediaPlayer = MediaPlayer.create(SplashActivity.this, R.raw.song);
        myMediaPlayer.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);

        /* TODO: implement bunny hopping animation
        bunnyPath = new Path();
        bunnyPath.arcTo(0f, 10, 0, 10, 700f, 180f, false);
        tealBunnyImageView = (ImageView) findViewById(R.id.tealBunnyImageView);
        tealBunnyObjectAnimator = ObjectAnimator.ofFloat(tealBunnyImageView, "x", "y", bunnyPath);
        tealBunnyObjectAnimator.setDuration(1500);
        tealBunnyObjectAnimator.setRepeatCount(3);
        tealBunnyObjectAnimator.start();
        */
    }
}
