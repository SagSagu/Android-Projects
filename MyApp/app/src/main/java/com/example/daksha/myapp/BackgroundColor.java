package com.example.daksha.myapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class BackgroundColor extends AppCompatActivity {

    private Button btnBackground;
    private PopupMenu popupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background_color_layout);

        btnBackground=(Button)findViewById(R.id.btnBackground);
        btnBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu=new PopupMenu(BackgroundColor.this, btnBackground);
                popupMenu.getMenuInflater().inflate(R.menu.popup_main,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle()=="Red"){
                            btnBackground.setBackgroundColor(Color.RED);
                        } else if(item.getTitle()=="Blue"){
                            btnBackground.setBackgroundColor(Color.BLUE);
                        } else {
                            btnBackground.setBackgroundColor(Color.BLACK);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }
}
