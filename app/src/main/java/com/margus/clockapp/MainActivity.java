package com.margus.clockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.margus.clockapp.visuals.ClockView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openClockView(View view) {
        Intent intentToOpenClockView = new Intent(MainActivity.this, ClockActivity.class);
        startActivity(intentToOpenClockView);
    }
}
