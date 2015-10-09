package com.tenaris.rodrigo.tenarisbarcodelocator;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;



/**
 * Controls the TenarisLogo Screen Activity. Defines time before starting application.
 */
public class TenarisLogoActivity extends Activity {
    //Milli-Seconds
    private static int CHANGE_SCREEN_TIMER = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenaris_logo);

        new Handler().postDelayed(new Runnable() {
        /*
         * Shows screen with a timer.
         */

            @Override
            public void run() {
                //Go to main activity once timer runs out.
                Intent i = new Intent(TenarisLogoActivity.this, MainMenuActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, CHANGE_SCREEN_TIMER);
    }
}
