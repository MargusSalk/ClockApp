package com.margus.clockapp;

import android.os.Handler;

import com.margus.clockapp.visuals.ClockView;

public class Ticker {
    // Refresh after ms;
    private long mRefreshRate;
    // Seconds in minute - how many real time seconds / per minute - basically speed of the clock
    private final int mSecondsInMinute;
    private final ClockView mClockView;
    private final Handler mHandler;

    public Ticker(int secondsInMinute, ClockView clockView) {
        this.mSecondsInMinute = secondsInMinute;
        this.mRefreshRate = 1000 / secondsInMinute;
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

    public int getSecondsInMinute() {
        return this.mSecondsInMinute;
    }
}
