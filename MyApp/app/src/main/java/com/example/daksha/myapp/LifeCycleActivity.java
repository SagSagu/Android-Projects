package com.example.daksha.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class LifeCycleActivity extends AppCompatActivity {
    String msg="Android: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.life_cycle_layout);
        Toast.makeText(getBaseContext(),"onCreate Called",Toast.LENGTH_SHORT).show();
        Log.d(msg,"onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getBaseContext(),"onStart Called",Toast.LENGTH_SHORT).show();
        Log.d(msg,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getBaseContext(),"onResume Called",Toast.LENGTH_SHORT).show();
        Log.d(msg,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getBaseContext(),"onPause Called",Toast.LENGTH_SHORT).show();
        Log.d(msg,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getBaseContext(),"onStop Called",Toast.LENGTH_SHORT).show();
        Log.d(msg,"onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getBaseContext(),"onRestart Called",Toast.LENGTH_SHORT).show();
        Log.d(msg,"onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getBaseContext(),"onDestroy Called",Toast.LENGTH_SHORT).show();
        Log.d(msg,"onDestroy");
    }


}
