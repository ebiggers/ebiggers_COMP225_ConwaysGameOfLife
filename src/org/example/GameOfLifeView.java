
package org.example;

import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.util.Log;

public class GameOfLifeView extends View {

    private GameOfLife life;

    private float width;
    private float height;

    public GameOfLifeView(Context context) {
        super(context);
        life = (GameOfLife)context;
        setFocusable(true);
        setFocusableInTouchMode(true);
        //setID(42);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }


    protected void onDraw(Canvas canvas) {
        Paint black = new Paint();
        black.setColor(Color.BLACK);
        canvas.drawRect(0, 0, getWidth(), getHeight(), black);

        Paint green = new Paint();
        green.setColor(Color.GREEN);
        int grid_width = life.getGridWidth();
        int grid_height = life.getGridHeight();
        float x_pix_per_cell = getWidth() / (float)grid_width;
        float y_pix_per_cell = getHeight() / (float)grid_height;
        for (int i = 0; i < life.getGridWidth(); i++) {
            for (int j = 0; j < life.getGridHeight(); j++) {
                if (life.getCellAt(i, j) != 0) {
                    Log.d("GameOfLife", "Draw (" + i + ", " + j + ")\n");
                    canvas.drawRect(x_pix_per_cell * i,
                                    y_pix_per_cell * j,
                                    x_pix_per_cell * (i + 1),
                                    y_pix_per_cell * (j + 1),
                                    green);
                }
            }
        }
    }
}
