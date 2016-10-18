package com.example.daksha.myapp;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MassageActivity extends AppCompatActivity {

    private long[] pattern;

    public void massage(View view){
        switch (view.getId()){
            case R.id.btnMoonShot:
                Vibrator moonShot = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                pattern = new long[]{0,400,100,900};
                moonShot.vibrate(pattern,2);
                break;
            case R.id.btnSunShot:
                Vibrator sunShot = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                pattern = new long[]{0,900,100,400};
                sunShot.vibrate(pattern,2);
                break;
            case R.id.btnStarShot:
                Vibrator starShot = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                pattern = new long[]{0,400,100,500,100,900};
                starShot.vibrate(pattern,2);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.massage_layout);
    }
}
