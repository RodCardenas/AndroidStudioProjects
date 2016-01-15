package rod.fitnesstracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class InternalDBActivity extends AppCompatActivity
{
    private DataManager datasource;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //TODO: Add data trending functionality (display rolling 10 day weight average, etc.)
        setContentView(R.layout.activity_internal_db);

        //Setup toolbar/actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);

        datasource = new DataManager(this);
        datasource.open();
    }

    public void onClick(View view)
    {
        @SuppressWarnings("unchecked")
        EditText currentWeight = (EditText) findViewById(R.id.weightET);
        EditText currentPushups = (EditText) findViewById(R.id.pushupET);
        EditText currentSitups = (EditText) findViewById(R.id.situpsET);
        EditText currentSquats = (EditText) findViewById(R.id.squatsET);
        EditText currentDistance = (EditText) findViewById(R.id.distanceET);
        DatePicker currentDate = (DatePicker) findViewById(R.id.datePicker);
        Data dta = null;

        switch (view.getId())
        {
            case R.id.save:

                if(currentWeight.getText().toString().matches(""))
                {
                    currentWeight.setText("0.0");
                }
                if(currentPushups.getText().toString().matches(""))
                {
                    currentPushups.setText("0");
                }
                if(currentSitups.getText().toString().matches(""))
                {
                    currentSitups.setText("0");
                }
                if(currentSquats.getText().toString().matches(""))
                {
                    currentSquats.setText("0");
                }
                if(currentDistance.getText().toString().matches(""))
                {
                    currentDistance.setText("0.0");
                }

                dta = datasource.createData(
                        createDate(currentDate),
                        Double.parseDouble(currentWeight.getText().toString()),
                        Integer.parseInt(currentPushups.getText().toString()),
                        Integer.parseInt(currentSitups.getText().toString()),
                        Integer.parseInt(currentSquats.getText().toString()),
                        Double.parseDouble(currentDistance.getText().toString()));
                currentWeight.setText("");
                currentPushups.setText("");
                currentSitups.setText("");
                currentSquats.setText("");
                currentDistance.setText("");

                Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    private long createDate(DatePicker dp)
    {
        long lDate;
        String dt;

        dt = Integer.toString(dp.getYear());

        lDate = dp.getMonth() + 1;
        if (lDate < 10)
            dt = dt + "0" + lDate;
        else dt = dt + Long.toString(lDate);

        lDate = dp.getDayOfMonth();
        if (lDate < 10)
            dt = dt + "0" + Long.toString(lDate);
        else dt = dt + Long.toString(lDate);

        return Long.valueOf(dt);
    }

    @Override
    protected void onResume()
    {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        datasource.close();
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle presses on the action bar items
        switch (item.getItemId())
        {
            case R.id.action_view_list:
                Intent myIntent = new Intent(InternalDBActivity.this, ViewDataActivity.class);
                startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.internal_db_activity_menu, menu);
        return true;
    }
}
