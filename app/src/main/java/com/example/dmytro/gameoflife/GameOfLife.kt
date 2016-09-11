package com.example.dmytro.gameoflife

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class GameOfLife : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val grid = findViewById(R.id.grid) as PixelGridView
        grid.numColumns = 15
        val mainTimer = Timer();
        mainTimer.schedule(object : TimerTask() {
            override fun run() {
                grid.invalidate()
            }
        }, 1000)
    }
}
