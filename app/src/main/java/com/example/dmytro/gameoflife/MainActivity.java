package com.example.dmytro.gameoflife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    private PixelGridView grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = (PixelGridView) findViewById(R.id.grid);
        grid.setNumColumns(15);
    }

}