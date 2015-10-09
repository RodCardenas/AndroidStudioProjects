package com.tenaris.rodrigo.graphicslessonapp;
import android.graphics.Canvas;

/**
 * Created by Rodrigo on 1/7/2015.
 * Updates logic in the game.
 */

public class GameLoopThread extends Thread
{
    static final long FPS = 30;
    private GameEngineView view;
    private boolean GameLoopIsRunning = false;

    public GameLoopThread(GameEngineView GEView)
    {
        this.view = GEView;
    }

    public void setGameLoopIsRunning(boolean IsLoopRunning)
    {
        GameLoopIsRunning = IsLoopRunning;
    }

    @Override
    public void run()
    {
        long ticksPS = 1000 / FPS;
        long StartTime, SleepTime;
        while (GameLoopIsRunning)
        {
            Canvas c = null;
            StartTime = System.currentTimeMillis();
            try
            {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder())
                {
                    view.onDraw(c);
                }
            } finally
            {
                if (c != null)
                {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
            SleepTime = ticksPS - (System.currentTimeMillis() - StartTime);
            try
            {
                if (SleepTime > 0)
                    sleep(SleepTime);
                else
                    sleep(10);
            } catch (Exception e)
            {
            }
        }
    }
}