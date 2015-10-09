package rod.codelibrary.gps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import rod.codelibrary.R;

public class aGPSMainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_actions_gps, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        ImageView imgView = (ImageView)findViewById(R.id.gpsImg);

        // Handle presses on the action bar items
        switch (item.getItemId())
        {
            case R.id.action_invisible_image:
                if(imgView.getVisibility() == View.INVISIBLE)
                    imgView.setVisibility(View.VISIBLE);
                else imgView.setVisibility(View.INVISIBLE);
                return true;
            case R.id.action_toggle_gps:
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getGPSLocation(View view)
    {
        Toast toast = Toast.makeText(view.getContext(),"Getting location...",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

        //TODO Write GPS Location getting Code.
    }

}

