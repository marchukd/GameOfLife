package com.example.dmytro.gameoflife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private PixelGridView grid;
    private Timer mainTimer = new Timer();
    private int frequencyOfUpdating = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = (PixelGridView) findViewById(R.id.grid);
        grid.setNumColumns(15);
        mainTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                grid.invalidate();
            }
        }, frequencyOfUpdating);
    }

}