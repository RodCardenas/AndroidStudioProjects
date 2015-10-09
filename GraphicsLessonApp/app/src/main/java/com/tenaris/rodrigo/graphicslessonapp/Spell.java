package com.tenaris.rodrigo.graphicslessonapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Rodrigo on 1/10/2015.
 */
public class Spell
{
    private static final int BMP_COLUMNS = 4;
    private int x,y;
    private Bitmap spellBMP;
    private int damage;
    private int level;
    private Sprite sprite;
    private int height, width;

    public Spell(Sprite s, Bitmap bmp)
    {
        sprite = s;
        spellBMP = bmp;
        height = bmp.getHeight();
        width = bmp.getWidth()/BMP_COLUMNS;
        damage = 5;
        level = 2;
    }

    public void onDraw(Canvas canvas, int castCount)
    {
        update(castCount);
        int srcX = sprite.direction * width;
        Rect src = new Rect(srcX, 0, srcX + width, height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(spellBMP, src, dst, null);
    }

    public void update(int multiplier)
    {
        switch(sprite.direction)
        {
            case 0: //Down
                x = sprite.x;
                y = sprite.y + sprite.height * multiplier;
                break;
            case 1: //Left
                x = sprite.x - sprite.width * multiplier;
                y = sprite.y;
                break;
            case 2: //Right
                x = sprite.x + sprite.width * multiplier;
                y = sprite.y;
                break;
            case 3: //Up
                x = sprite.x;
                y = sprite.y - sprite.height * multiplier;
                break;
        }

    }
}
