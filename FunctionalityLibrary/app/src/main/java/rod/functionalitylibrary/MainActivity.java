package rod.functionalitylibrary;

import android.app.Activity;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends Activity
{
    private static final String TAG = "Main Activity";
    TextView locationText;
    ListView listView;
    Location currentLocation;
    List<Location> locationList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentLocation = new Location("-",0.0,0.0);
        locationText = (TextView) findViewById(R.id.location_tv);
        listView = (ListView) findViewById(R.id.savedLocations_lv);

        ListView3ColumnAdapter listView3ColumnAdapter = new ListView3ColumnAdapter();
        listView.setAdapter(listView3ColumnAdapter);
    }

    // Called when user clicks buttons
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.getLocation_b:
                try {
                    LocationManager locMan = (LocationManager) getSystemService(LOCATION_SERVICE);

                    Criteria fineCriteria = new Criteria();
                    fineCriteria.setAccuracy(Criteria.ACCURACY_FINE);

                    locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10000, new LocationListener() {
                        @Override
                        public void onLocationChanged(android.location.Location location) {

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

                    android.location.Location curLocation = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    currentLocation.SetDescrption("Yes");//new SimpleDateFormat("MM/dd/yy hh:mm").format(new Date()));
                    currentLocation.SetLatitude(curLocation.getLatitude());
                    currentLocation.SetLongitude(curLocation.getLongitude());

                    locationText.setText( currentLocation.timeStamp + ", " + currentLocation.latitude + ", " + currentLocation.longitude);

                } catch (Exception e) {
                    // Log any error messages to LogCat using Log.e()
                    Log.e(TAG, e.toString());
                }
                break;
            case R.id.saveLocation_b:
                try {
                } catch (Exception e) {
                    // Log any error messages to LogCat using Log.e()
                    Log.e(TAG, e.toString());
                }
                break;
        }
    }


    public class ListView3ColumnAdapter extends BaseAdapter
    {
        List<Location> locationList;

        public ListView3ColumnAdapter()
        {
            locationList = getDataForListView();
        }

        /***
         *getCount() : This method tells the listview the number of rows it will require.
         * This count can come from your data source. It can be the size of your Data Source.
         * If you have your datasource as a list of objects, this value will be the size of the list.
         */
        @Override
        public int getCount()
        {
            return locationList.size();
        }

        /***
         * Object getItem(int arg0): This method returns an object. This method helps ListView to
         * get data for each row. The parameter passed is the row number starting from 0.
         * In our List of Objects, this method will return the object at the passed index.
         */
        @Override
        public Location getItem(int position)
        {
            return locationList.get(position);
        }

        /***
         * long getItemId(int arg0) : You can ignore this method.
         * It just returns the same value as passed. This in general helps ListView to map
         * its rows to the data set elements.
         */
        @Override
        public long getItemId(int position)
        {
            return position;
        }

        /***
         * getView : This is the most important method. This method will be called to get the View
         * for each row. This is the method where we can use our custom listitem and bind it with
         * the data. The fist argument passed to getView is the listview item position ie row number.
         * The second parameter is recycled view reference(as we know listview recycles a view, you
         * can confirm through this parameter). Third parameter is the parent to which this view
         * will get attached to.
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            /* Inflate the xml to get a view */
            /* Optimization to save the overhead of inflating a new view for each row */
            if(convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(getApplicationContext());
                convertView = inflater.inflate(R.layout.locations_listview_3_column, parent);
            }

            /* Get reference to the objects to populate in layout */
            TextView locDescription = (TextView)convertView.findViewById(R.id.description_tv);
            TextView locLatitude = (TextView)convertView.findViewById(R.id.latitude_tv);
            TextView locLongitude = (TextView)convertView.findViewById(R.id.longitude_tv);

            /* Populate the layout references with data from item in list matching the passed parameter's position */
            Location locationOfInterest = getItem(position);
            locDescription.setText(locationOfInterest.timeStamp);
            locLatitude.setText(locationOfInterest.latitude);
            locLongitude.setText(locationOfInterest.longitude);

            return convertView;
        }
    }

    public class Location
    {
        String timeStamp;
        String latitude;
        String longitude;

        public Location()
        {
            this.timeStamp = "-";
            this.latitude = "0";
            this.longitude= "0";
        }
        public Location(String timeStamp, double Latitude, double Longitude)
        {
            this.timeStamp = timeStamp;
            this.latitude = Double.toString(Latitude);
            this.longitude= Double.toString(Longitude);
        }

        public Location (String timeStamp, String Latitude, String Longitude)
        {
            this.timeStamp = timeStamp;
            this.latitude = Latitude;
            this.longitude= Longitude;
        }

        public void SetDescrption(String Description)
        {
            this.timeStamp = Description;
            return;
        }


        public void SetLatitude(Double Latitude)
        {
            this.latitude = Double.toString(Latitude);
            return;
        }

        public void SetLongitude(Double Longitude)
        {
            this.latitude = Double.toString(Longitude);
            return;
        }
    }

    public List<Location> getDataForListView()
    {
        List<Location> locations = new ArrayList<Location>();

        for(int i=0;i<4;i++)
        {

            Location local = new Location();
            local.timeStamp = new SimpleDateFormat("MM/dd/yy hh:mm").format(new Date(System.currentTimeMillis()));
            local.latitude = Integer.toString(i*4+22);
            local.longitude = Integer.toString(i/3+22);
        }
        return locations;
    }
}