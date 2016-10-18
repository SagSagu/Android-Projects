package com.example.daksha.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Daksha on 10/18/2016.
 */

public class NewsActivity extends AppCompatActivity {

    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);

        Bundle bundle = getIntent().getExtras();

        tvMessage=(TextView)findViewById(R.id.tvMessage);
        tvMessage.setText(bundle.getCharSequence("Message"));

    }
}
