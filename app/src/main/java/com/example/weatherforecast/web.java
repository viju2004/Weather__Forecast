package com.example.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class web extends AppCompatActivity {
WebView wv;
ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        wv=findViewById(R.id.wv);

        Intent i1=getIntent();
        String cit=i1.getStringExtra("city");

        WebViewClient wc=new WebViewClient();
        wv.setWebViewClient(wc);

//        wv.loadUrl("https://www.weather-forecast.com/locations/"+cit+"/forecasts/latest");
      String u="https://www.google.com/search?q=todays+weather+in+"+cit+"&oq=todays+weather+in+"+cit+"&aqs=chrome..69i57.8444j0j1&sourceid=chrome&ie=UTF-8";
        wv.loadUrl(u);
        WebSettings wes=wv.getSettings();
        wes.setJavaScriptEnabled(false);


        pd=new ProgressDialog(this);
        pd.setIndeterminate(true);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Fetching Weather Information");
        pd.setCancelable(false);
        pd.show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                pd.dismiss();
            }
        }, 2300);

    }
}