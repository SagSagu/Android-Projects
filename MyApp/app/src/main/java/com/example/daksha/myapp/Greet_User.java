package com.example.daksha.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Daksha on 10/12/2016.
 */

public class Greet_User  extends AppCompatActivity{

    private static  final String TAG = Greet_User.class.getSimpleName();
    private TextView tvFirstName;
    private TextView tvSecondName;
    private TextView tvGreet;

    public void greet(View view){

        Log.i(TAG, "Greet User Method");

        String fName = tvFirstName.getText().toString();
        String sName = tvSecondName.getText().toString();

        if(TextUtils.isEmpty(fName)||TextUtils.isEmpty(sName)) {
            tvGreet = (TextView) findViewById(R.id.tvGreet);
            tvGreet.setText("Please Enter Details");
            Toast.makeText(getBaseContext(),"Please Enter Details", Toast.LENGTH_SHORT).show();
            return;
        } else{
            tvGreet = (TextView) findViewById(R.id.tvGreet);
            tvGreet.setText("Greetings " + fName + " " + sName);
            Toast.makeText(getBaseContext(),"Greetings" + " " + fName + " " + sName, Toast.LENGTH_SHORT).show();
         }

        Log.i(TAG, fName + " " + sName);



    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greetuser_layout);

            tvFirstName = (TextView) findViewById(R.id.tvFirstName);
            tvSecondName = (TextView) findViewById(R.id.tvSecondName);

    }
}
