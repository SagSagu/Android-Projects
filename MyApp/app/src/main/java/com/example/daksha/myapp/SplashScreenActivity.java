package com.example.daksha.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView ivGokuDbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=new Intent(this,Greet_User.class);
        startActivity(intent);
        finish();

    }
}
