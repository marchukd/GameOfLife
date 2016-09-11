package com.example.dmytro.gameoflife

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class GameOfLife : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val grid = findViewById(R.id.grid) as PixelGridView
        val mainTimer = Timer()
        val matrix = grid.cells
        getNextGeneration(matrix)
        mainTimer.schedule(object : TimerTask() {
            override fun run() = grid.postInvalidate()
        }, 1000)
    }

    fun getCountOfNeighbors(x: Int, y: Int, mtrx: Array<BooleanArray>): Int {
        var countOfNeighbors = 0;

        if (mtrx[x - 1][y]) countOfNeighbors++
        if (mtrx[x - 1][y - 1]) countOfNeighbors++
        if (mtrx[x][y - 1]) countOfNeighbors++
        if (mtrx[x + 1][y - 1]) countOfNeighbors++
        if (mtrx[x + 1][y]) countOfNeighbors++
        if (mtrx[x + 1][y + 1]) countOfNeighbors++
        if (mtrx[x][y + 1]) countOfNeighbors++
        if (mtrx[x - 1][y + 1]) countOfNeighbors++
        return countOfNeighbors
    }

    fun getNextGeneration(matrix:Array<BooleanArray>):Array<BooleanArray> {
        val nextGeneration = matrix.clone()
        for (rowIndex in matrix.indices)
            for (collIndex in matrix[rowIndex].indices) {
                val numOfNeig = getCountOfNeighbors(collIndex, rowIndex, matrix)
                if (matrix[rowIndex][collIndex]) {
                    if (numOfNeig !in 2..3)
                        nextGeneration[rowIndex][collIndex] = false
                }
                else {
                    if(numOfNeig == 3)
                        nextGeneration[rowIndex][collIndex] = true
                }
            }
        return nextGeneration
    }
}
