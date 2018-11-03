package com.margus.clockapp;

import android.content.Context;

import com.margus.clockapp.visuals.ClockView;

class Ticker {
    // Refresh after @mRefreshRate ms;
    private float mRefreshRate;
    private final ClockView mClockView;

    public Ticker(int ticksPerSecond, ClockView clockView) {
        this.mRefreshRate = 1000 / ticksPerSecond;
        this.mClockView = clockView;
    }

    public void start() {
    }
}
