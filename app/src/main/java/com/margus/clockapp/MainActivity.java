package com.margus.clockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonDisplayClock) {
            Intent intentToOpenClockActivity = new Intent(MainActivity.this, ClockActivity.class);
            startActivity(intentToOpenClockActivity);
        }
    }
}
