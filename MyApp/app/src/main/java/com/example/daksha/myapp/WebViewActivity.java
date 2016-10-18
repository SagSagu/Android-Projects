package com.example.daksha.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    private WebView wvFelight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);

        wvFelight=(WebView)findViewById(R.id.wvFelight);
        wvFelight.getSettings().setJavaScriptEnabled(true);
        wvFelight.loadUrl("http://www.google.com");

    }
}
