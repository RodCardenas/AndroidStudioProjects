package rod.game;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.io.IOException;

public class SplashActivity extends Activity
{
 
    // used to know if the back button was pressed in the splash screen activity and avoid opening the next activity
    private boolean mIsBackButtonPressed;
    private static final int SPLASH_DURATION = 2000; // 2 seconds
 
 
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
 
	    //Remove title bar
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	
	    //Remove notification bar
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_splash);
 
        Handler handler = new Handler();
        startAnimation();
        startMusic();       
 
        // run a thread after 2 seconds to start the home screen
        handler.postDelayed(new Runnable()
        {
 
            @Override
            public void run() 
            {
 
                // make sure we close the splash screen so the user won't come back when it presses back key
 
                finish();
                 
                if (!mIsBackButtonPressed) 
                {
                    // start the home screen if the back button wasn't pressed already 
                    Intent startApp = new Intent(SplashActivity.this, LandingActivity.class);
                    SplashActivity.this.startActivity(startApp);
               }
                 
            }
 
        }, SPLASH_DURATION); // time in milliseconds (1 second = 1000 milliseconds) until the run() method will be called
 
    }
 
    @Override
   public void onBackPressed()
    {
 
        // set the flag to true so the next activity won't start up
        mIsBackButtonPressed = true;
        super.onBackPressed();
 
    }
    
    public void startAnimation()
    {
    	 ImageView logo = (ImageView)findViewById(R.id.rodIcon);
         Animation shakeNbake = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
         logo.startAnimation(shakeNbake);
    }
    
    public void startMusic()
    {
    	MediaPlayer player = new MediaPlayer();
        try {
			player.setDataSource("/storage/sdcard0/Sounds/Zoom.3ga");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			player.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        player.start();
    	
    }
}
