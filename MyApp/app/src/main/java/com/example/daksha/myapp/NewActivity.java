package com.example.daksha.myapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Daksha on 10/7/2016.
 */

public class NewActivity extends AppCompatActivity {

    public void doSomething(View view){
        TextView textView = (TextView) findViewById(R.id.tvResult);
        textView.setText("Hello guys");
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(50.5F);
        LinearLayout col = (LinearLayout) findViewById(R.id.layout);
        col.setBackgroundColor(Color.BLACK);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newactivity_layout);


    }
}
