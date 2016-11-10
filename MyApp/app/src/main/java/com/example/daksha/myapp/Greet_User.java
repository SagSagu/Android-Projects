package com.example.daksha.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daksha.myapp.utils.Util;

import static com.example.daksha.myapp.LoginUser.LOGOUT;

/**
 * Created by Daksha on 10/12/2016.
 */

public class Greet_User  extends AppCompatActivity{

    private static  final String TAG = Greet_User.class.getSimpleName();
    private TextView tvFirstName;
    private TextView tvSecondName;
    private TextView tvGreet;

    private SharedPreferences sharedPreferences;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_account_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.myAccount){
            startActivity(new Intent(getBaseContext(),MyAccountActivity.class));
        }

        if(id == R.id.logout){
            startActivity(new Intent(getBaseContext(),LoginUser.class));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(LOGOUT,"LOGOUT");
            editor.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greetuser_layout);

            tvFirstName = (TextView) findViewById(R.id.tvFirstName);
            tvSecondName = (TextView) findViewById(R.id.tvSecondName);

        sharedPreferences = getSharedPreferences(UserRegistrationActivity.UD_PREF,MODE_PRIVATE);

    }
}
