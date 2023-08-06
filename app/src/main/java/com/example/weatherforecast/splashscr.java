package com.example.weatherforecast;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;

public class splashscr extends AppCompatActivity {
GifImageView sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscr);
//        getSupportActionBar().hide();


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over


                Intent i = new Intent(splashscr.this, Whether.class);
                startActivity(i);
                finish();
            }
        }, 6000);

    }
}