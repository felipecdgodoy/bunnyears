package com.example.bunnyears.ui.main;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.bunnyears.PictureActivity;
import com.example.bunnyears.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class PictureTab extends Fragment {
    Uri imageUri;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
         //dispatchTakePictureIntent();
        View view = inflater.inflate(R.layout.picturetab,container, false);
        return view;
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public void onResume() {
        super.onResume();
        //takePhoto();
        //dispatchTakePictureIntent();
    }
    public Bitmap rotateBitmap90(Bitmap bInput) {
        float degrees = 90; //rotation degree
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees);
        return Bitmap.createBitmap(bInput, 0, 0, bInput.getWidth(), bInput.getHeight(), matrix, true);
    }
}
