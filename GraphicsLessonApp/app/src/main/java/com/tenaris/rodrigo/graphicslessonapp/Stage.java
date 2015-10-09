package com.tenaris.rodrigo.graphicslessonapp;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Rodrigo on 1/8/2015.
 */
public class Stage
{
    private Context context;
    public Bitmap stageBMP;
    public int width;
    public int height;

    public Stage (Context con, Bitmap bmp)
    {
        context = con;
        stageBMP = bmp;
        height = con.getResources().getDisplayMetrics().heightPixels;
        width = con.getResources().getDisplayMetrics().widthPixels;
    }

    public void onDraw(Canvas canvas)
    {
        Rect dst = new Rect(0, 0, width, height);
        canvas.drawBitmap(stageBMP, null, dst, null);
    }
}
