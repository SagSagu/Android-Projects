package com.example.daksha.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;

/**
 * Created by Daksha on 10/19/2016.
 */

public class SplashScreenDemoActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreenDemoActivity.this,NewActivity.class);
                SplashScreenDemoActivity.this.startActivity(mainIntent);
                SplashScreenDemoActivity.this.finish();
            }
        }, SPLASH_DISPLAY);
    }
}
