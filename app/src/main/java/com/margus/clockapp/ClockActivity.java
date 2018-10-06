package com.margus.clockapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.margus.clockapp.visuals.ClockView;

public class ClockActivity extends AppCompatActivity {

    private ClockView mClockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        mClockView = findViewById(R.id.clock_view);
    }
}
