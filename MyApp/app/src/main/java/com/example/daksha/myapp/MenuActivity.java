package com.example.daksha.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Daksha on 10/17/2016.
 */

public class MenuActivity extends AppCompatActivity {

    /*public void displayToast(View view){
        switch (view.getId()){
            case R.id.settings:
                Toast.makeText(getBaseContext(),"Settings is selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Toast.makeText(getBaseContext(),"About is selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nothing:
                Toast.makeText(getBaseContext(),"Nothing is selected",Toast.LENGTH_SHORT).show();
                break;
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.settings)
            Toast.makeText(getBaseContext(),"Settings is selected",Toast.LENGTH_SHORT).show();
        else if(item.getItemId()==R.id.about)
            Toast.makeText(getBaseContext(),"About is selected",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getBaseContext(),"Nothing is selected",Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
