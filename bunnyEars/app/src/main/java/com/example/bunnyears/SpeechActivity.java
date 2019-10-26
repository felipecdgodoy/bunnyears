package com.example.bunnyears;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class SpeechActivity extends AppCompatActivity {

    private TextToSpeech myTTS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initializeTextToSpeech();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myTTS.shutdown();
    }

    private void initializeTextToSpeech() {
        myTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(myTTS.getEngines().size() == 0) {
//                    Toast.makeText(MainActivity.this, "this device doesn't support voice input",
//                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    myTTS.setLanguage(Locale.US);
                    speak("I have an important message: Josh is a cara de baunilha");
                }
            }

            private void speak(String message) {
                if(Build.VERSION.SDK_INT >= 21) {
                    myTTS.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
                } else {
                    myTTS.speak(message, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }

}
