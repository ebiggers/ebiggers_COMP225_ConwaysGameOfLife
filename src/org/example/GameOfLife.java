
package org.example;

import android.app.Activity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class GameOfLife extends Activity implements Handler.Callback
{

    private GameOfLifeView lifeView;
    private byte[][] grid;
    private byte[][] grid2;
    private Thread updaterThread;
    private Handler handler;

    //private class UpdateGrid extends TimerTask {
        //public void run() {
            //step();
        //}
    //}

    private void step() {
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                int num_neighbors = 0;
                num_neighbors += grid[i][j + 1];
                num_neighbors += grid[i][j - 1];
                num_neighbors += grid[i - 1][j - 1];
                num_neighbors += grid[i - 1][j];
                num_neighbors += grid[i - 1][j + 1];
                num_neighbors += grid[i + 1][j - 1];
                num_neighbors += grid[i + 1][j];
                num_neighbors += grid[i + 1][j + 1];
                if (num_neighbors == 3 || (grid[i][j] != 0 && num_neighbors == 2))
                    grid2[i][j] = 1;
                else
                    grid2[i][j] = 0;
            }
        }
        byte[][] tmp = grid;
        grid = grid2;
        grid2 = tmp;
    }

    public boolean handleMessage(Message msg) {
        lifeView.invalidate();
        return true;
    }

    public int getGridWidth() {
        return grid.length;
    }

    public int getGridHeight() {
        return grid[0].length;
    }

    public byte getCellAt(int x, int y) {
        return grid[x][y];
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifeView = new GameOfLifeView(this);
        setContentView(lifeView);
        lifeView.requestFocus();

        grid = new byte[60][60];
        grid2 = new byte[60][60];
        grid[30][30] = 1;
        grid[31][29] = 1;
        grid[31][30] = 1;
        grid[31][31] = 1;
        grid[32][31] = 1;

        handler = new Handler(this);

        updaterThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    step();
                    handler.sendMessage(new Message());
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) { }
                }
            }
        });
        updaterThread.start();
    }
}
