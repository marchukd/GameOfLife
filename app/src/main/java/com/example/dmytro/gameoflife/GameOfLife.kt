package com.example.dmytro.gameoflife

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*

class GameOfLife : AppCompatActivity() {
    private var timer: Timer = Timer()
    private var game: Game? = null
    private var generationCounter = 0
    private var titleOfApp = "Game of Life"
    private val speedGame: Long = 250

    private fun startGame() {
        game = Game(grid.numRows, grid.numColumns, grid.cells)
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    grid.cells = game!!.nextGeneration()
                    generationCounter++
                    title = "$titleOfApp ($generationCounter)"
                }
            }
        }, 0, speedGame)
    }

    private fun stopGame() {
        timer.cancel()
        title = titleOfApp
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu);
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.start -> startGame()
            R.id.stop -> stopGame()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()
        stopGame()
    }
}
