package rod.codelibrary;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

// Shows examples of Action Bar Logo Implementation, Custom Action Menu Buttons, and Activity Transition
public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_USE_LOGO|ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        ImageView imgView = (ImageView)findViewById(R.id.logoImg);

        // Handle presses on the action bar items
        switch (item.getItemId())
        {
            case R.id.action_invisible_image:
                if(imgView.getVisibility() == View.INVISIBLE)
                    imgView.setVisibility(View.VISIBLE);
                else imgView.setVisibility(View.INVISIBLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToDataManagementActivity(View view)
    {
        Intent myIntent = new Intent(MainActivity.this, rod.codelibrary.dataManagement.aDataManagementMainActivity.class);
        startActivity(myIntent);
    }

    public void goToGPSActivity(View view)
    {
        Intent myIntent = new Intent(MainActivity.this, rod.codelibrary.gps.aGPSMainActivity.class);
        startActivity(myIntent);
    }

}

