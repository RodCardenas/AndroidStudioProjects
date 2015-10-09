package com.tenaris.rodrigo.graphicslessonapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Rodrigo on 1/4/2015.
 * Updates view/graphics in the game.
 */
public class GameEngineView extends SurfaceView
{
    private SurfaceHolder holder;
    public GameLoopThread gameLoopThread;
    private List<Sprite> sprites;
    private Stage stage;

    public GameEngineView(Context context)
    {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback()
        {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder)
            {
                boolean retry = true;
                gameLoopThread.setGameLoopIsRunning(false);
                while (retry)
                {
                    try
                    {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e)
                    {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder)
            {
                gameLoopThread.setGameLoopIsRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height){}
        });
        stage = new Stage(getContext(), BitmapFactory.decodeResource(getResources(), R.drawable.area1));
        sprites = new ArrayList<Sprite>();
        sprites.add(new Sprite(this,BitmapFactory.decodeResource(getResources(), R.drawable.hero),"Hero"));
        sprites.add(new Sprite(this,BitmapFactory.decodeResource(getResources(), R.drawable.evil_witch),"Witch"));
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        stage.onDraw(canvas);
        for (Sprite sprite : sprites)
        {
            sprite.onDraw(canvas);
        }
    }
}