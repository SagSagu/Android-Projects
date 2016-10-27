package com.example.daksha.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class BroadcastReceiverActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_receiver_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // broadcast a custom intent.
    public void broadcastIntent(View view){
        Intent intent = new Intent();
        intent.setAction("sagar.CUSTOM_INTENT");
        sendBroadcast(intent);
    }
}
