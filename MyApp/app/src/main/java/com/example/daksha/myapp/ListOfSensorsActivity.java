package com.example.daksha.myapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ListOfSensorsActivity extends AppCompatActivity {

    private TextView tvResult;

    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_sensors_layout);

        tvResult = (TextView) findViewById(R.id.tvResult);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        StringBuilder str = new StringBuilder();
        for(Sensor s:deviceSensors){
            str.append("\n" + s.getName() + " " + s.getPower());
            tvResult.setText(str);
        }
    }
}
