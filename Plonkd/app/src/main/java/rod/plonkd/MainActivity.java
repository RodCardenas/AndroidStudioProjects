package rod.plonkd;

import android.app.Activity;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends Activity {

    private final String TAG = "MainActivity";
    private TextView locationText;
    private Locations savedLocations[];
    private String convertedLocations[][];
    private ListView listView;
    private ArrayAdapter adapter;
    private int choice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Required call through to Activity.onCreate()
        // Restore any saved instance state
        super.onCreate(savedInstanceState);

        // Set content view
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        locationText = (TextView) findViewById(R.id.location_tv);
        listView = (ListView) findViewById(R.id.savedLocations_lv);

        //Initialize memory elements
        savedLocations = new Locations[]{
                new Locations("Mexico City, MX", 19.4284700, -99.1276600),
                new Locations("San Juan, PR", 18.466334, -66.105722),
                new Locations("Houston, TX", 29.7632800, -95.3632700),
                new Locations("Somewhere?", 35.3535353, 53.5353535)
        };

        convertedLocations = new String[4][4];
        convertLocationsToArray();

        adapter = new ArrayAdapter<String>(
                this,
                R.layout.locations_layout,
                R.id.description_tv,
                convertedLocations[3]);
        listView.setAdapter(adapter);
    }

    public void convertLocationsToArray()
    {
        for(int i=0;i<savedLocations.length;i++)
        {
            convertedLocations[0][i] = savedLocations[i].description;
            convertedLocations[1][i] = Double.toString(savedLocations[i].latitude);
            convertedLocations[2][i] = Double.toString(savedLocations[i].longitude);
            convertedLocations[3][i] = convertedLocations[0][i]+ ", " + convertedLocations[1][i] + ", " + convertedLocations[2][i];
        }
    }

    // Called when user clicks buttons
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getLocation_b:
                try {
                    LocationManager locMan = (LocationManager) getSystemService(LOCATION_SERVICE);

                    Criteria fineCriteria = new Criteria();
                    fineCriteria.setAccuracy(Criteria.ACCURACY_FINE);

                    locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10000, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {

                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    });

                    Location currentLocation = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    locationText.setText(currentLocation.getLatitude() + ", " + currentLocation.getLongitude());

                } catch (Exception e) {
                    // Log any error messages to LogCat using Log.e()
                    Log.e(TAG, e.toString());
                }
                break;
            case R.id.saveLocation_b:
                try { //TODO prompt for description and array position to modify
                    Locations toBeSaved = new Locations(
                            new SimpleDateFormat("MM/dd/yy hh:mm").format(new Date()),
                            Double.parseDouble(locationText.getText().subSequence(0,10).toString()),
                            Double.parseDouble(locationText.getText().subSequence(13, locationText.length()).toString()));

                    CharSequence options[] = new CharSequence[] {"1","2","3","4"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Save to Which Slot?");
                    builder.setItems(options, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            choice = which;
                        }
                    });
                    builder.show();

                    savedLocations[choice] = toBeSaved;
                    convertLocationsToArray();
                    adapter.notifyDataSetInvalidated();
                } catch (Exception e) {
                    // Log any error messages to LogCat using Log.e()
                    Log.e(TAG, e.toString());
                }
                break;
        }
    }
//TODO Save data even when app closes

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.i(TAG, "The activity is visible and about to be started.");
    }


    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.i(TAG, "The activity is visible and about to be restarted.");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i(TAG, "The activity is and has focus (it is now \"resumed\")");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i(TAG,
                "Another activity is taking focus (this activity is about to be \"paused\")");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i(TAG, "The activity is no longer visible (it is now \"stopped\")");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(TAG, "The activity is about to be destroyed.");
    }

    public class Locations
    {
        private String description;
        private double latitude;
        private double longitude;

        public Locations(String Description, double Latitude, double Longitude)
        {
            this.description = Description;
            this.latitude = Latitude;
            this.longitude = Longitude;
        }
    }
}

//TODO Check if GPS is on. If not request user to activate.