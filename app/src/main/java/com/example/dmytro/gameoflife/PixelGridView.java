package com.example.dmytro.gameoflife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmytro on 11.09.2016
 */
public class PixelGridView extends View {
    private int numColumns = 15, numRows;
    private float cellWidth, cellHeight;
    private Paint blackPaint = new Paint();
    private boolean[][] cellChecked;
    private List<List<Boolean>> mtrx;

    public PixelGridView(Context context) {
        this(context, null);
    }

    public PixelGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        calculateDimensions();
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
        calculateDimensions();
    }

    public boolean[][] getCells() {
        return cellChecked;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getNumRows() {
        return numRows;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();
    }

    private void calculateDimensions() {
        cellWidth = (float) getWidth() / (float) numColumns;
        cellHeight = cellWidth;
        numRows = Math.round(getHeight() / cellHeight);
        cellChecked = new boolean[numColumns][numRows];
        float overCellsSpace = getHeight() - (cellHeight * numRows);
        if (overCellsSpace != 0)
            setLayoutParams(new RelativeLayout.LayoutParams(getWidth(), Math.round(getHeight() - overCellsSpace)));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        if (numColumns == 0 || numRows == 0) {
            return;
        }

        int width = getWidth();
        int height = getHeight();

        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                if (cellChecked[i][j]) {

                    canvas.drawRect(i * cellWidth, j * cellHeight,
                            (i + 1) * cellWidth, (j + 1) * cellHeight,
                            blackPaint);
                }
            }
        }

        for (int i = 1; i < numColumns; i++) {
            canvas.drawLine(i * cellWidth, 0, i * cellWidth, height, blackPaint);
        }

        for (int i = 1; i < numRows; i++) {
            canvas.drawLine(0, i * cellHeight, width, i * cellHeight, blackPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int column = (int) (event.getX() / cellWidth);
            int row = (int) (event.getY() / cellHeight);

            cellChecked[column][row] = !cellChecked[column][row];
            invalidate();
        }

        return true;
    }
}