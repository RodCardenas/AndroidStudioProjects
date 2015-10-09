package com.tenaris.rodrigo.tenarisbarcodelocator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tenaris.rodrigo.tenarisbarcodelocator.R;

public class MainMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        TestDatabaseActivity.datasource = new CommentsDataSource(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void GoToScanActivity(View view) {
        Intent intent = new Intent(MainMenuActivity.this, MainActivity.class);
        startActivity(intent);

        // close this activity
        finish();
    }

    public void GoToHistoricalScans(View view){
        Intent intent = new Intent(MainMenuActivity.this, TestDatabaseActivity.class);
        startActivity(intent);

        // close this activity
        finish();
    }

    public void Reload(View view){
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    public void About(View view){
        openAlert(view);
    }

    private void openAlert(final View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainMenuActivity.this);

        alertDialogBuilder.setTitle(R.string.app_shortcut_name);
        alertDialogBuilder.setMessage("Developed by Rodrigo Cardenas ®Copyright 2014.\nNo warranties expressed or implied.");
        alertDialogBuilder.setIcon(R.drawable.ic_launcher);

        // Set Scan again button
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // go to a new activity of the app
                Reload(view);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }
}
