package com.example.bunnyears;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class ScoreActivity extends AppCompatActivity {
    List<Float> confidences;
    List<String> results;
    List<String> words;
    Uri uri;
    public TextToSpeech myTTS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        confidences = convertF((float[])getIntent().getExtras().get("confidences"));
        results = convertS(asStrings((Object[])getIntent().getExtras().get("results")));
        words = convertS(asStrings((Object[])getIntent().getExtras().get("words")));
        uri = (Uri)getIntent().getExtras().get("uri");
        Button tryAgain = findViewById(R.id.retry_button);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ScoreActivity.this, PictureActivity.class);
                myIntent.putExtra("uri", uri.toString());
                ScoreActivity.this.startActivity(myIntent);
            }
        });
        Button done = findViewById(R.id.done_button);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ScoreActivity.this, MainActivity.class);
                myIntent.putExtra("uri", uri.toString());
                ScoreActivity.this.startActivity(myIntent);
            }
        });
        Button playback = findViewById(R.id.playback_button);
        playback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(String.join(" ", words));
            }
        });
        int score = Scorer.score(results, words);
        TextView scoreView = findViewById(R.id.score);
        scoreView.setText(Integer.toString(score));

        Log.e("correct","correctly got " + words);
        Log.e("correct","correctly got " + confidences);
        Log.e("correct", "correctly got " + results);
        initializeTextToSpeech();
    }
    public static String[] asStrings(Object... objArray) {
        String[] strArray = new String[objArray.length];
        for (int i = 0; i < objArray.length; i++)
            strArray[i] = String.valueOf(objArray[i]);
        return strArray;
    }
    public static List<Float> convertF(float[] input) {
        ArrayList<Float> returnVal = new ArrayList<>();
        for (float in : input) {
            returnVal.add(in);
        }
        return returnVal;
    }
    public static List<String> convertS(String[] input) {
        ArrayList<String> returnVal = new ArrayList<>();
        for (String in : input) {
            returnVal.add(in);
        }
        return returnVal;
    }


    private void speak(String message) {
        myTTS.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void initializeTextToSpeech() {
        myTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(myTTS.getEngines().size() == 0) {
                    speak("Your device does not support speech recognition");
                    finish();
                } else {
                    myTTS.setLanguage(Locale.US);
                    speak("Press playback to hear the solution");
                }
            }
        });
    }
}
