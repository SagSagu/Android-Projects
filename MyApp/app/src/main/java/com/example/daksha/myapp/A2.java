package com.example.daksha.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class A2 extends AppCompatActivity {

    private EditText etUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a2_layout);
        etUserName=(EditText)findViewById(R.id.etUserName);
    }

    public void save(View view){

        String name = etUserName.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("username", name);
        setResult(RESULT_OK, intent);
        finish();
    }
}
