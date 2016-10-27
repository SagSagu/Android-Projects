package com.example.daksha.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewActivity extends AppCompatActivity {

    private WebView wvResult;
    private ProgressBar pbWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);
        wvResult = (WebView) findViewById(R.id.wvFelight);
        pbWeb = (ProgressBar) findViewById(R.id.pbWeb);


    }

    @Override
    protected void onStart() {
        super.onStart();



        wvResult.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                pbWeb.setVisibility(View.INVISIBLE);
                super.onPageFinished(view, url);
            }
        });

        WebSettings webSettings = wvResult.getSettings();
        webSettings.setJavaScriptEnabled(true);

        wvResult.loadUrl("file:///android_asset/htmlfile.html");





        wvResult.setWebChromeClient(new WebChromeClient());
    }
}
