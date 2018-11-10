package com.margus.clockapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.margus.clockapp.visuals.ClockView;

public class ClockActivity extends AppCompatActivity {

    private ClockView mClockView;
    private Ticker mTicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        mClockView = findViewById(R.id.clock_view);
        mClockView.setColor(getString(R.string.pref_color_red_value));
        mTicker = new Ticker(60, mClockView);
        mClockView.setTicker(mTicker);

        mTicker.start();
    }
}
