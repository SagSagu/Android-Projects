package com.example.daksha.myapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;

public class ImageSharingActivity extends AppCompatActivity {


    ImageView ivGoku;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_sharing_layout);
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        ivGoku = (ImageView) findViewById(R.id.ivGoku);
        Uri receivedUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (receivedUri != null) {
            ivGoku.setImageURI(receivedUri);

        }


    }
}


