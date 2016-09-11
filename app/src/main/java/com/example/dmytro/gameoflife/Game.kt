package com.example.dmytro.gameoflife

/**
 * Created by Dmytro on 11.09.2016
 */
class Game(private val cols: Int, private val rows: Int, var currentGeneration: Array<BooleanArray>) {

    fun nextGeneration(): Array<BooleanArray> {
        val nextGeneration = cloneGeneration()

        for (i in currentGeneration.indices)
            for (j in currentGeneration[i].indices) {
                val numOfNeighbors = getCountOfNeighbors(i, j)
                if (currentGeneration[i][j]) {
                    if (numOfNeighbors !in 2..3)
                        nextGeneration[i][j] = false
                } else {
                    if (numOfNeighbors == 3)
                        nextGeneration[i][j] = true
                }
            }
        currentGeneration = nextGeneration.clone()
        return nextGeneration
    }

    private fun getCountOfNeighbors(x: Int, y: Int): Int {
        var countOfNeighbors = 0
        if (isNeighbor(x - 1, y, currentGeneration)) countOfNeighbors++
        if (isNeighbor(x - 1, y - 1, currentGeneration)) countOfNeighbors++
        if (isNeighbor(x, y - 1, currentGeneration)) countOfNeighbors++
        if (isNeighbor(x + 1, y - 1, currentGeneration)) countOfNeighbors++
        if (isNeighbor(x + 1, y, currentGeneration)) countOfNeighbors++
        if (isNeighbor(x + 1, y + 1, currentGeneration)) countOfNeighbors++
        if (isNeighbor(x, y + 1, currentGeneration)) countOfNeighbors++
        if (isNeighbor(x - 1, y + 1, currentGeneration)) countOfNeighbors++
        return countOfNeighbors
    }

    private fun isNeighbor(x: Int, y: Int, mtrx: Array<BooleanArray>): Boolean {
        try {
            return mtrx[x][y]
        } catch (e: Exception) {
            return false
        }
    }

    private fun cloneGeneration(): Array<BooleanArray> {
        val nextGeneration = Array(cols, { BooleanArray(rows) })
        for (i in currentGeneration.indices)
            nextGeneration[i] = currentGeneration[i].clone()
        return nextGeneration
    }
}