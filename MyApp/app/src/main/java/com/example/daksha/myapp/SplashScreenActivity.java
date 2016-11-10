package com.example.daksha.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static com.example.daksha.myapp.LoginUser.LOGOUT;
import static com.example.daksha.myapp.LoginUser.NREG;
import static com.example.daksha.myapp.UserRegistrationActivity.UD_PREF;
import static com.example.daksha.myapp.UserRegistrationActivity.UP_KEY;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView ivGokuDbs;

    private SharedPreferences sharedPreferences;

    private String logout;
    private String reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);

        sharedPreferences = getSharedPreferences(UD_PREF,MODE_PRIVATE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //loadPreferences();

        logout = sharedPreferences.getString(LOGOUT,null);
        reg = sharedPreferences.getString(NREG,null);

        Handler handler = new Handler();

        if(LOGOUT.equals(logout)) {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getBaseContext(), LoginUser.class));
                    finish();
                }
            },3000);

        } else if(NREG.equals(reg)){

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getBaseContext(), UserRegistrationActivity.class));
                    finish();
                }
            },3000);

        } else {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getBaseContext(), Greet_User.class));
                    finish();
                }
            },3000);

        }

    }

}
