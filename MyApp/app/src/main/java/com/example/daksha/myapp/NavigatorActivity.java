package com.example.daksha.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class NavigatorActivity extends AppCompatActivity {

    public void navigation(View view){
        switch (view.getId()){
            case R.id.btnGreetUser:
                startActivity(new Intent(NavigatorActivity.this, Greet_User.class));
                break;
            case R.id.btnSimpleCalculator:
                startActivity(new Intent(NavigatorActivity.this, SimpleCalculatorActivity.class));
                break;
            case R.id.btnWebView:
                startActivity(new Intent(NavigatorActivity.this, WebViewActivity.class));
                break;
            case R.id.btnBenchmark:
                startActivity(new Intent(NavigatorActivity.this, BenchmarkingActivity.class));
                break;
            case R.id.btnMenuOption:
                startActivity(new Intent(NavigatorActivity.this, MenuActivity.class));
                break;
            case R.id.btnGoogleNews:
                Intent google = new Intent(getApplication(),NewsActivity.class);
                google.putExtra("Message","Google News");
                startActivity(google);
                break;
            case R.id.btnFelightNews:
                Intent felight = new Intent(getApplication(),NewsActivity.class);
                felight.putExtra("Message","Felight News");
                startActivity(felight);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigator_layout);
    }
}
