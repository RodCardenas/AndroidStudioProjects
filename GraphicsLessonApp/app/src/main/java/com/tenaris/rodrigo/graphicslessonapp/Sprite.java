package com.tenaris.rodrigo.graphicslessonapp;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

/**
 * Created by Rodrigo on 1/7/2015.
 */

public class Sprite {
    private static final String TAG = "Sprite";
    //Movement
    private GameEngineView gameView;
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;
    private int currentFrame = 0;
    public int x;
    public int y;
    public int direction;
    public int width;
    public int height;
    private boolean isActive;

    //Attributes
    private String name;
    private Bitmap spriteBMP;
    private int hp = 10;
    private int mp = 10;

    //Spells
    private Spell spell;
    private boolean castSpell;
    private int castCount;

    public Sprite(GameEngineView GEView, Bitmap bmp, String n)
    {
        width = bmp.getWidth() / BMP_COLUMNS;
        height = bmp.getHeight() / BMP_ROWS;
        gameView = GEView;
        spriteBMP = bmp;
        direction = 0;
        isActive = false;
        x = new Random().nextInt(gameView.getWidth() + 1);
        y = new Random().nextInt(gameView.getHeight() + 1);
        name = n;
    }

    private void update()
    {
        if (StartActivity.isControlsNotPressed() && StartActivity.isNothingPressed())
        {
            Log.d(TAG,"sa.x = " + StartActivity.x + " sa.y = " + StartActivity.y + " x = " + x + " y = " + y);
            if (StartActivity.x >= x && StartActivity.x <= x + width && StartActivity.y >= y && StartActivity.y <= y + height)
            {
                isActive = true;
                Log.d(TAG, name + " just became isActive = " + isActive);
            }else
            {
                isActive = false;
                Log.d(TAG, name + " just became isActive = " + isActive);
            }


        }
        if(isActive)
        {
            //Update the Direction based on user input
            if (StartActivity.isDownPressed())
            {
                y += 5;
                direction = 0;
            }

            if (StartActivity.isRightPressed())
            {
                x += 5;
                direction = 2;
            }
            if (StartActivity.isLeftPressed())
            {
                x -= 5;
                direction = 1;
            }
            if (StartActivity.isUpPressed())
            {
                y -= 5;
                direction = 3;
            }

            if (StartActivity.isAPressed() && StartActivity.isBPressed()) //Fireball
            {
                if (spell == null)  spell = new Spell(this, BitmapFactory.decodeResource(gameView.getResources(), R.drawable.fireball));
                castSpell = true;

                if (castCount > 3) castCount = 0;
                castCount += 1;
            }

            if (StartActivity.isAPressed() && !StartActivity.isNothingPressed() && !StartActivity.isBPressed())    //Run
            {
                switch (direction)
                {
                    case 0: //Down
                        y += 10;
                        break;
                    case 1: //Left
                        x -= 10;
                        break;
                    case 2: //Right
                        x += 10;
                        break;
                    case 3: //Up
                        y -= 10;
                        break;
                }
            }
            if (StartActivity.isBPressed() && !StartActivity.isAPressed()) //Teleport
            {
                x = new Random().nextInt(gameView.getWidth() + 1);
                y = new Random().nextInt(gameView.getHeight() + 1);
                StartActivity.BPressed = false;
            }

            //Prevent Character from leaving screen
            if (x >= gameView.getWidth() - width) x = gameView.getWidth() - width;
            if (x <= 0) x = 0;
            if (y >= gameView.getHeight() - height) y = gameView.getHeight() - height;
            if (y <= 0) y = 0;
            //Keep Sprite without moving if D-Pad hasn't been activated
            if (!StartActivity.isNothingPressed()) currentFrame = (currentFrame + 1) % BMP_COLUMNS;
            else currentFrame = 1;
        }
    }

    public void onDraw(Canvas canvas)
    {
        //Log.d(TAG, "onDraw of " + name);
        update();
        //Log.d(TAG, name + " x = " + x + " y = " + y);
        int srcX = currentFrame * width;
        int srcY = direction * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(spriteBMP, src, dst, null);

        if (castSpell)
        {
            spell.onDraw(canvas, castCount);
            castSpell = false;
        }
    }
}

