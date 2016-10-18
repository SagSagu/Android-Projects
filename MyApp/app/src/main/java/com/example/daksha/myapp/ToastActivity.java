package com.example.daksha.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Daksha on 10/17/2016.
 */

public class ToastActivity extends AppCompatActivity {

    private Button btnTopLeft,btnTopCenter,btnTopRight;
    private Button btnMiddleLeft,btnMiddleCenter,btnMiddleRight;
    private Button btnBottomLeft,btnBottomCenter,btnBottomRight;

    public void toastMessage(View view){
        switch (view.getId()){
            case R.id.btnTopLeft:
               Toast toastTL = Toast.makeText(getBaseContext(),"Top Left",Toast.LENGTH_SHORT);
                toastTL.setGravity(Gravity.TOP|Gravity.LEFT,130,130);
                toastTL.show();
                break;
            case R.id.btnTopCenter:
                Toast toastTC = Toast.makeText(getBaseContext(),"Top Center",Toast.LENGTH_SHORT);
                toastTC.setGravity(Gravity.TOP|Gravity.CENTER,0,130);
                toastTC.show();
                break;
            case R.id.btnTopRight:
                Toast toastTR = Toast.makeText(getBaseContext(),"Top Right",Toast.LENGTH_SHORT);
                toastTR.setGravity(Gravity.TOP|Gravity.RIGHT,130,130);
                toastTR.show();
                break;
            case R.id.btnMiddleLeft:
                Toast toastML = Toast.makeText(getBaseContext(),"Middle Left",Toast.LENGTH_SHORT);
                toastML.setGravity(Gravity.CENTER|Gravity.LEFT,130,20);
                toastML.show();
                break;
            case R.id.btnMiddleCenter:
                Toast toastMC = Toast.makeText(getBaseContext(),"Middle Center",Toast.LENGTH_SHORT);
                toastMC.setGravity(Gravity.CENTER|Gravity.CENTER,0,20);
                toastMC.show();
                break;
            case R.id.btnMiddleRight:
                Toast toastMR = Toast.makeText(getBaseContext(),"Middle Right",Toast.LENGTH_SHORT);
                toastMR.setGravity(Gravity.CENTER|Gravity.RIGHT,130,20);
                toastMR.show();
                break;
            case R.id.btnBottomLeft:
                Toast toastBL = Toast.makeText(getBaseContext(),"Bottom Left",Toast.LENGTH_SHORT);
                toastBL.setGravity(Gravity.BOTTOM|Gravity.LEFT,130,100);
                toastBL.show();
                break;
            case R.id.btnBottomCenter:
                Toast toastBC = Toast.makeText(getBaseContext(),"Bottom Center",Toast.LENGTH_SHORT);
                toastBC.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,100);
                toastBC.show();
                break;
            case R.id.btnBottomRight:
                Toast toastBR = Toast.makeText(getBaseContext(),"Bottom Right",Toast.LENGTH_SHORT);
                toastBR.setGravity(Gravity.BOTTOM|Gravity.RIGHT,130,100);
                toastBR.show();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast_layout);

        btnTopLeft=(Button)findViewById(R.id.btnTopLeft);
        btnTopCenter=(Button)findViewById(R.id.btnTopCenter);
        btnTopRight=(Button)findViewById(R.id.btnTopRight);
        btnMiddleLeft=(Button)findViewById(R.id.btnMiddleLeft);
        btnMiddleCenter=(Button)findViewById(R.id.btnMiddleCenter);
        btnMiddleRight=(Button)findViewById(R.id.btnMiddleRight);
        btnBottomLeft=(Button)findViewById(R.id.btnBottomLeft);
        btnBottomCenter=(Button)findViewById(R.id.btnBottomCenter);
        btnBottomRight=(Button)findViewById(R.id.btnBottomRight);

    }
}
