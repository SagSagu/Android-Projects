package com.example.daksha.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ContextMenuActivity extends AppCompatActivity {

    private TextView tvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.context_menu_layout);

        tvMenu=(TextView)findViewById(R.id.tvMenu);
        registerForContextMenu(tvMenu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("DoSomething");
        menu.add("DoEverything");
        menu.add("DoNothing");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="DoSomething"){
            Toast.makeText(getBaseContext(),"DoSomething",Toast.LENGTH_SHORT).show();
        }else if(item.getTitle()=="DoEverything"){
            Toast.makeText(getBaseContext(),"DoEverything",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getBaseContext(),"DoNothing",Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }
}
