package com.example.bunnyears;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton movie = findViewById(R.id.movieButton);
        movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream song1 = this.getClass().getClassLoader().getResourceAsStream("/res/drawable/lilostitch.jpg");
                String s = getPackageName();
                PackageManager m = getPackageManager();
                try {
                    PackageInfo p = m.getPackageInfo(s, 0);
                    s = p.applicationInfo.dataDir;
                    Uri song1Url = Uri.parse("android.resource://com.example.bunnyears/" + R.drawable.lilostitch2);
                    Intent myIntent = new Intent(MainActivity.this, PictureActivity.class);
                    //myIntent.putExtra("image", imageBitmap); //Optional parameters
                    myIntent.putExtra("uri", song1Url.toString());
                    Log.e("Test",song1Url.toString());
                    startActivityForResult(myIntent, REQUEST_TAKE_PHOTO);
                    ; //<- pseudo code
                }
                catch (Exception e) {
                    Log.e("something","went very wrong");
                }
            }
        });

        ImageButton book = findViewById(R.id.bookButton);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream song1 = this.getClass().getClassLoader().getResourceAsStream("/res/drawable/lilostitch.jpg");
                String s = getPackageName();
                PackageManager m = getPackageManager();
                try {
                    PackageInfo p = m.getPackageInfo(s, 0);
                    s = p.applicationInfo.dataDir;
                    Uri song1Url = Uri.parse("android.resource://com.example.bunnyears/" + R.drawable.alice);
                    Intent myIntent = new Intent(MainActivity.this, PictureActivity.class);
                    //myIntent.putExtra("image", imageBitmap); //Optional parameters
                    myIntent.putExtra("uri", song1Url.toString());
                    Log.e("Test",song1Url.toString());
                    startActivityForResult(myIntent, REQUEST_TAKE_PHOTO);
                    ; //<- pseudo code
                }
                catch (Exception e) {
                    Log.e("something","went very wrong");
                }
            }
        });

        ImageButton children = findViewById(R.id.childrenButton);
        children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream song1 = this.getClass().getClassLoader().getResourceAsStream("/res/drawable/peterrabbit.jpg");
                String s = getPackageName();
                PackageManager m = getPackageManager();
                try {
                    PackageInfo p = m.getPackageInfo(s, 0);
                    s = p.applicationInfo.dataDir;
                    Uri song1Url = Uri.parse("android.resource://com.example.bunnyears/" + R.drawable.peterrabbit);
                    Intent myIntent = new Intent(MainActivity.this, PictureActivity.class);
                    //myIntent.putExtra("image", imageBitmap); //Optional parameters
                    myIntent.putExtra("uri", song1Url.toString());
                    Log.e("Test",song1Url.toString());
                    startActivityForResult(myIntent, REQUEST_TAKE_PHOTO);
                    ; //<- pseudo code
                }
                catch (Exception e) {
                    Log.e("something","went very wrong");
                }
            }
        });

        ImageButton alph = findViewById(R.id.alphabetButton);
        alph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int images = 7;
                Random random = new Random();
                int selection = random.nextInt(images + 1);
                Uri song1Url;
                switch (selection) {
                    case 0:
                        song1Url = Uri.parse("android.resource://com.example.bunnyears/" + R.drawable.print1);
                        break;
                    case 1:
                        song1Url = Uri.parse("android.resource://com.example.bunnyears/" + R.drawable.print2);
                        break;
                    case 2:
                        song1Url = Uri.parse("android.resource://com.example.bunnyears/" + R.drawable.print3);
                        break;
                    case 3:
                        song1Url = Uri.parse("android.resource://com.example.bunnyears/" + R.drawable.print4);
                        break;
                    case 4:
                        song1Url = Uri.parse("android.resource://com.example.bunnyears/" + R.drawable.print5);
                        break;
                    case 5:
                        song1Url = Uri.parse("android.resource://com.example.bunnyears/" + R.drawable.print6);
                        break;
                    case 6:
                        song1Url = Uri.parse("android.resource://com.example.bunnyears/" + R.drawable.print7);
                        break;
                    default:
                        song1Url = Uri.parse("android.resource://com.example.bunnyears/" + R.drawable.print1);
                        break;
                }
                InputStream song1 = this.getClass().getClassLoader().getResourceAsStream("/res/drawable/lilostitch.jpg");
                String s = getPackageName();
                PackageManager m = getPackageManager();
                try {
                    PackageInfo p = m.getPackageInfo(s, 0);
                    s = p.applicationInfo.dataDir;
                    Intent myIntent = new Intent(MainActivity.this, PictureActivity.class);
                    //myIntent.putExtra("image", imageBitmap); //Optional parameters
                    myIntent.putExtra("uri", song1Url.toString());
                    Log.e("Test",song1Url.toString());
                    startActivityForResult(myIntent, REQUEST_TAKE_PHOTO);
                    ; //<- pseudo code
                }
                catch (Exception e) {
                    Log.e("something","went very wrong");
                }
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
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
    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    static final int REQUEST_TAKE_PHOTO = 1;
    Uri fileUri;
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(MainActivity.this.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                //...
                Log.e("test","couldNotread");
            }
            //Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(MainActivity.this,
                        "com.example.bunnyears.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                this.fileUri = photoURI;
                //takePictureIntent.putExtra("uri", photoURI.toString());
                //takePictureIntent.putExtra("file", photoFile);
                //takePictureIntent.putExtra("uriString", photoURI.toString());
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            //File photoFile = (File) extras.get("file");
            //String photoURI = (String) data.getExtras().get("data").toString();
            Log.e("PicAct", fileUri.toString());
            Intent myIntent = new Intent(MainActivity.this, PictureActivity.class);
            //myIntent.putExtra("image", imageBitmap); //Optional parameters
             myIntent.putExtra("uri", fileUri.toString());
            //myIntent.putExtra("file", photoFile);
            MainActivity.this.startActivity(myIntent);
        }
    }
}
