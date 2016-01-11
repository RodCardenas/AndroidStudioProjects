package rod.fitnesstracker;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.List;

public class InternalDBActivity extends ListActivity
{
    private DataManager datasource;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_db);

        datasource = new DataManager(this);
        datasource.open();

        List<Data> values = datasource.getAllData();
        ArrayAdapter<Data> adapter = new ArrayAdapter<Data>(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    public void onClick(View view)
    {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Data> adapter = (ArrayAdapter<Data>) getListAdapter();
        EditText currentWeight = (EditText) findViewById(R.id.weightET);
        EditText currentPushups = (EditText) findViewById(R.id.pushupET);
        EditText currentSitups = (EditText) findViewById(R.id.situpsET);
        EditText currentSquats = (EditText) findViewById(R.id.squatsET);
        EditText currentDistance = (EditText) findViewById(R.id.distanceET);
        DatePicker currentDate = (DatePicker) findViewById(R.id.datePicker);
        Data dta = null;

        switch (view.getId())
        {
            case R.id.add:
                dta = datasource.createData(
                        createDate(currentDate),
                        Double.parseDouble(currentWeight.getText().toString()),
                        Integer.parseInt(currentPushups.getText().toString()),
                        Integer.parseInt(currentSitups.getText().toString()),
                        Integer.parseInt(currentSquats.getText().toString()),
                        Double.parseDouble(currentDistance.getText().toString()));
                adapter.add(dta);
                currentWeight.setText("");
                currentPushups.setText("");
                currentSitups.setText("");
                currentSquats.setText("");
                currentDistance.setText("");
                break;

            case R.id.delete:
                if (getListAdapter().getCount() > 0)
                {
                    dta = (Data) getListAdapter().getItem(0);
                    datasource.deleteData(dta);
                    adapter.remove(dta);
                }
                break;
        }
        adapter.notifyDataSetChanged();
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
}
