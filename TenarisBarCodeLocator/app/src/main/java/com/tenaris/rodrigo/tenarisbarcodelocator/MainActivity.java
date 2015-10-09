package com.tenaris.rodrigo.tenarisbarcodelocator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import junit.framework.Test;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class MainActivity extends Activity implements ZXingScannerView.ResultHandler {
    private static final String TAG = "thelog";
    private ZXingScannerView mScannerView;
    public static String theResult;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        Toast.makeText(getApplicationContext(), "Found: " + rawResult.getText() + "\nCode Format: " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_LONG).show();
        openSaveAlert(mScannerView, rawResult);
    }

    public void scanAgain(){
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    public void GoToMainMenu(){
        Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    public void GoToDatabase(){
        Intent intent = new Intent(MainActivity.this, TestDatabaseActivity.class);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    private void openSaveAlert(View view, final Result r) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setTitle(R.string.app_shortcut_name);
        alertDialogBuilder.setMessage("Store Scan?");
        alertDialogBuilder.setIcon(R.drawable.ic_launcher);

        // Set Scan again button
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // go to a new activity of the app
                theResult = r.getText();
                TestDatabaseActivity.datasource.open();
                TestDatabaseActivity.datasource.createComment(theResult);
                TestDatabaseActivity.datasource.close();
                Toast.makeText(getApplicationContext(), "Saved: " + theResult, Toast.LENGTH_SHORT).show();
                openExitAlert(mScannerView);
            }
        });

        // Set the Exit App button
        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                Toast.makeText(getApplicationContext(), "Did not Save: " + r.getText(), Toast.LENGTH_SHORT).show();
                openExitAlert(mScannerView);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }

    private void openExitAlert(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setTitle(R.string.app_shortcut_name);
        alertDialogBuilder.setMessage("What do you want to do?");
        alertDialogBuilder.setIcon(R.drawable.ic_launcher);

        // Set Scan again button
        alertDialogBuilder.setPositiveButton("Scan Again",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                // go to a new activity of the app
                scanAgain();
            }
        });

        // Set Main Menu App button
        alertDialogBuilder.setNeutralButton("Main Menu",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                // exit the app and go to the HOME
                GoToMainMenu();
            }
        });

        // Set the Exit App button
        alertDialogBuilder.setNegativeButton("See Database",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                //MainActivity.this.finish();
                GoToDatabase();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }
}
