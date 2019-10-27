package com.example.bunnyears;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.MediaStore;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class PictureActivity extends AppCompatActivity {
    android.widget.ImageView ImageView;
    private TextRecognizer detector;
    private SpeechRecognizer mySpeechRecognizer;
    private TextView scanResults;
    List<String> wordResults;
    float[] confidences;
    private TextToSpeech myTTS;
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    private List<String> currResults;

    public List<String> getCurrResults() {
        return currResults;
    }

    public float[] getConfidences() {
        return confidences;
    }

    public List<String> getWordResults() {
        return wordResults;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pictureresult);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Uri bundle = Uri.parse(getIntent().getStringExtra("uri"));
        //String photoURI = (String) bundle.toString();
        //scanResults = (TextView) findViewById(R.id.textView);
        Log.e("PicAct", bundle.toString());
        wordResults = new ArrayList<>();
        confidences = new float[0];
        currResults = new ArrayList<>();
        Button recButton = findViewById(R.id.record_button);
        Button submitButton = findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PictureActivity.this, ScoreActivity.class);
                Log.e("test33", getCurrResults().toString());
                Log.e("test33", getWordResults().toString());
                myIntent.putExtra("results", getCurrResults().toArray());
                myIntent.putExtra("confidences", getConfidences());
                myIntent.putExtra("words", getWordResults().toArray());
                myIntent.putExtra("uri", bundle);
                PictureActivity.this.startActivity(myIntent);
            }
        });
        recButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(PictureActivity.this,
                        Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED) {
                    //speak("You did not grant the app permission to use your microphone");
                    if (ActivityCompat.shouldShowRequestPermissionRationale(PictureActivity.this,
                            Manifest.permission.RECORD_AUDIO)) {
                    } else {
                        ActivityCompat.requestPermissions(PictureActivity.this,
                                new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
                    }
                } else {
                    // Permission has been granted
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
                    mySpeechRecognizer.startListening(intent);
                }
            }
        });
        initializeSpeechRecognizer();
        try {
            //Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), bundle);
            //bitmap = getResizedBitmap(bitmap,1280,1024);
            ImageView = (ImageView) findViewById(R.id.imageView);
            TextRecognizer textRecognizer = new TextRecognizer.Builder(this).build();
            try {
                Bitmap bitmap = decodeBitmapUri(this, bundle);
                bitmap = rotateBitmap90(bitmap);
                detector = new TextRecognizer.Builder(getApplicationContext()).build();
                if (detector.isOperational() && bitmap != null) {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> textBlocks = detector.detect(frame);
                    String blocks = "";
                    String lines = "";
                    String words = "";
                    for (int index = 0; index < textBlocks.size(); index++) {
                        //extract scanned text blocks here
                        TextBlock tBlock = textBlocks.valueAt(index);
                        blocks = blocks + tBlock.getValue() + "\n" + "\n";
                        for (Text line : tBlock.getComponents()) {
                            //extract scanned text lines here
                            lines = lines + line.getValue() + "\n";
                            for (Text element : line.getComponents()) {
                                //extract scanned text words here
                                words = words + element.getValue() + ", ";
                                wordResults.add(element.getValue());
                            }
                        }
                    }
                    if (textBlocks.size() == 0) {
//                        scanResults.setText("Scan Failed: Found nothing to scan");
                    } else {
//                        scanResults.setText(scanResults.getText() + "Blocks: " + "\n");
//                        scanResults.setText(scanResults.getText() + blocks + "\n");
//                        scanResults.setText(scanResults.getText() + "---------" + "\n");
//                        scanResults.setText(scanResults.getText() + "Lines: " + "\n");
//                        scanResults.setText(scanResults.getText() + lines + "\n");
//                        scanResults.setText(scanResults.getText() + "---------" + "\n");
//                        scanResults.setText(scanResults.getText() + "Words: " + "\n");
//                        scanResults.setText(scanResults.getText() + words + "\n");
//                        scanResults.setText(scanResults.getText() + "---------" + "\n");
                    }
                } else {
                    scanResults.setText("Could not set up the detector!");
                }
                ImageView.setImageBitmap(bitmap);
                Log.e("res", wordResults.toString());
                List<String> trimmedStrings = new ArrayList<String>();
                for(String s : wordResults) {
                    trimmedStrings.add(s.trim());
                }

                wordResults = trimmedStrings;
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load Image", Toast.LENGTH_SHORT)
                        .show();
                Log.e("test", e.toString());

            }
        } catch (Exception e) {

        }

    }
    private Bitmap decodeBitmapUri(Context ctx, Uri uri) throws FileNotFoundException {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(uri), null, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        bmOptions.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(ctx.getContentResolver()
                .openInputStream(uri), null, bmOptions);
    }
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }
    public Bitmap rotateBitmap90(Bitmap bInput) {
        float degrees = 90; //rotation degree
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees);
        return Bitmap.createBitmap(bInput, 0, 0, bInput.getWidth(), bInput.getHeight(), matrix, true);
    }

    private void initializeSpeechRecognizer() {
        if (SpeechRecognizer.isRecognitionAvailable(this)) {
            mySpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
            mySpeechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float rmsdB) {

                }

                @Override
                public void onBufferReceived(byte[] buffer) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int error) {

                }

                @Override
                public void onResults(Bundle bundle) {
                    currResults = Arrays.asList(bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION).get(0).split(" "));
                    confidences = bundle.getFloatArray(SpeechRecognizer.CONFIDENCE_SCORES);
                    Log.e("Tag", currResults.toString());
                    // TODO: Update screen with results
//                    sentence = results.get(0);
//                    processResults(sentence);
                    // processing goes here
                }

                @Override
                public void onPartialResults(Bundle partialResults) {

                }

                @Override
                public void onEvent(int eventType, Bundle params) {

                }
            });

        }
    }
    private void speak(String message) {
        myTTS.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeSpeechRecognizer();
    }
    public List<String> cleanList(List<String> input) {
        List<String> trimmedStrings = new ArrayList<String>();
        for(String s : input) {
            trimmedStrings.add(s.trim());
        }
        Set<String> set = new HashSet<>(trimmedStrings);
        trimmedStrings.clear();
        trimmedStrings.addAll(set);
        return trimmedStrings;
    }
}
