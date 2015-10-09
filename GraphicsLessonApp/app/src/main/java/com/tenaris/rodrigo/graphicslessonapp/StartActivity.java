package com.tenaris.rodrigo.graphicslessonapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;


public class StartActivity extends Activity implements View.OnTouchListener
{
    private static final String TAG = "StartActivity";
    public GameEngineView theGameView;
    public static boolean DownPressed = false;
    public static boolean RightPressed = false;
    public static boolean LeftPressed = false;
    public static boolean UpPressed = false;
    public static boolean APressed = false;
    public static boolean BPressed = false;
    public static boolean NothingPressed = true;
    public static boolean ControlsNotPressed = false;
    public static int x = 0,y = 0;
    private int gvID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controller);

        theGameView = new GameEngineView(this);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.MasterFrame);
        frameLayout.addView(theGameView);
        theGameView.setOnTouchListener(this);

        RelativeLayout relativeLayoutControls = (RelativeLayout) findViewById(R.id.ControllerLayout);
        relativeLayoutControls.bringToFront();

        ImageButton down = (ImageButton)findViewById(R.id.DownButton);
        down.setOnTouchListener(this);

        ImageButton right = (ImageButton)findViewById(R.id.RightButton);
        right.setOnTouchListener(this);

        ImageButton left = (ImageButton)findViewById(R.id.LeftButton);
        left.setOnTouchListener(this);

        ImageButton up = (ImageButton)findViewById(R.id.UpButton);
        up.setOnTouchListener(this);

        ImageButton a = (ImageButton)findViewById(R.id.AButton);
        a.setOnTouchListener(this);

        ImageButton b = (ImageButton)findViewById(R.id.BButton);
        b.setOnTouchListener(this);
    }

    public static boolean isDownPressed()
    {
        return DownPressed;
    }

    public static boolean isRightPressed()
    {
        return RightPressed;
    }

    public static boolean isLeftPressed()
    {
        return LeftPressed;
    }

    public static boolean isUpPressed()
    {
        return UpPressed;
    }

    public static boolean isAPressed()
    {
        return APressed;
    }

    public static boolean isBPressed()
    {
        return BPressed;
    }

    public static boolean isNothingPressed()
    {
        return NothingPressed;
    }
    public static boolean isControlsNotPressed()
    {
        return ControlsNotPressed;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        x = (int)event.getX();
        y = (int)event.getY();
        Log.d(TAG, "onTouch x = " + x + " y = " + y );
        switch (v.getId())
        {
            case R.id.DownButton:
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    DownPressed = true;
                    NothingPressed =false;
                }
                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    DownPressed = false;
                    NothingPressed =true;
                }
                break;
            case R.id.RightButton:
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    RightPressed = true;
                    NothingPressed =false;
                }
                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    RightPressed = false;
                    NothingPressed =true;
                }
                break;
            case R.id.LeftButton:
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    LeftPressed = true;
                    NothingPressed =false;
                }
                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    LeftPressed = false;
                    NothingPressed =true;
                }
                break;
            case R.id.UpButton:
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    UpPressed = true;
                    NothingPressed =false;
                }
                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    UpPressed = false;
                    NothingPressed =true;
                }
                break;
            case R.id.AButton:  //Run
                if(event.getAction() == MotionEvent.ACTION_DOWN)    APressed = true;
                if(event.getAction() == MotionEvent.ACTION_UP)      APressed = false;
                break;
            case R.id.BButton:  //Teleport
                if(event.getAction() == MotionEvent.ACTION_DOWN)    BPressed = true;
                break;
            default:
                Log.d(TAG, "onTouch no control touch x = " + x + " y = " + y );
                if(event.getAction() == MotionEvent.ACTION_DOWN)    ControlsNotPressed = true;
                if(event.getAction() == MotionEvent.ACTION_UP)    ControlsNotPressed = false;
                break;
        }
        return false;
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        theGameView.gameLoopThread.setGameLoopIsRunning(false);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        theGameView.gameLoopThread.setGameLoopIsRunning(true);
    }
}
