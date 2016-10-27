package com.example.daksha.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class A1 extends AppCompatActivity {

    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a1_layout);
    }

    public void getUserName(View view){
        Intent intent = new Intent(getBaseContext(),A2.class);
        startActivityForResult(intent,1);
        tvName=(TextView)findViewById(R.id.tvName);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            String name = intent.getStringExtra("username");
            tvName.setText(name);
        }

    }
}
