package com.example.testtask;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ActivityOneMain extends AppCompatActivity {


    private static final int COUNTDOWN_TIME_MILLIS = 3500;

    private volatile boolean mStopCountDownThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onResume() {
        super.onResume();
        startThread();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopThread();
    }


    public void startThread() {
        mStopCountDownThread = false;
        Runnable countDownRunnable = getCountDownRunnable();
        Thread thread = new Thread(countDownRunnable);
        thread.start();
    }

    private Runnable getCountDownRunnable() {
        Runnable countDownRunnable = () -> {

            try {
                Thread.sleep(COUNTDOWN_TIME_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!mStopCountDownThread) {
                runOnUiThread(() -> startRandomActivity());
            }

        };

        return countDownRunnable;
    }

    public void stopThread() {
        mStopCountDownThread = true;
    }


    private void startRandomActivity() {
        Random random = new Random();
        if (random.nextBoolean()) {
            startActivity(ActivityThreeRecycler.class);
        } else {
            startActivity(ActivityTwoWeb.class);
        }

    }


    private void startActivity(Class<?> activityClass) {
        Intent intent = new Intent(ActivityOneMain.this, activityClass);
        startActivity(intent);
    }


}