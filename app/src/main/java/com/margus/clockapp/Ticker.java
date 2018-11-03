package com.margus.clockapp;

import android.content.Context;
import android.os.Handler;

import com.margus.clockapp.visuals.ClockView;

public class Ticker {
    // Refresh after ms;
    private long mRefreshRate;
    private final int mTicksPerSecond;
    private final ClockView mClockView;
    private final Handler mHandler;

    public Ticker(int ticksPerSecond, ClockView clockView) {
        this.mTicksPerSecond = ticksPerSecond;
        this.mRefreshRate = 1000 / ticksPerSecond;
        this.mClockView = clockView;
        this.mHandler = new Handler();
    }

    public void start() {
        refreshView();
    }

    private void refreshView() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mClockView.updateView();
            }
        }, mRefreshRate);
    }

    public int getTicksPerSecond() {
        return this.mTicksPerSecond;
    }
}
