package com.example.daksha.myapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ShakeSensorActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;

    private Sensor mShake;

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shake_sensor_layout);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mShake = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        tvResult = (TextView) findViewById(R.id.tvResult);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        tvResult.setText("X:" + x + "\nY:" + y + "\nZ:" + z);

        if(x<-10 && x>-10.5){
            Toast.makeText(getBaseContext(),"Moved Left", Toast.LENGTH_SHORT).show();
        }
        if(x>10 && x<10.5){
            Toast.makeText(getBaseContext(),"Moved Right", Toast.LENGTH_SHORT).show();
        }
        if(y<-10 && y>-10.5){
            Toast.makeText(getBaseContext(),"Moved Bottom", Toast.LENGTH_SHORT).show();
        }
        if(y>10 && y<10.5){
            Toast.makeText(getBaseContext(),"Moved Top", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mShake, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
