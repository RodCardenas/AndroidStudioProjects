package rod.codelibrary.dataManagement;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import java.util.List;

import rod.codelibrary.R;


public class InternalDatabaseDataManagementActivity extends ListActivity
{
    private WeightsDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_data_management);

        datasource = new WeightsDataSource(this);
        datasource.open();

        List<Weight> values = datasource.getAllWeights();
        ArrayAdapter<Weight> adapter = new ArrayAdapter<Weight>(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    public void onClick(View view)
    {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Weight> adapter = (ArrayAdapter<Weight>) getListAdapter();
        EditText currentWeight = (EditText) findViewById(R.id.currentLocation);
        Weight weight = null;

        switch (view.getId())
        {
            case R.id.add:
                weight = datasource.createWeight(Double.parseDouble(currentWeight.getText().toString()));
                adapter.add(weight);
                currentWeight.setText("");
                break;

            case R.id.delete:
                if (getListAdapter().getCount() > 0)
                {
                    weight = (Weight) getListAdapter().getItem(0);
                    datasource.deleteWeight(weight);
                    adapter.remove(weight);
                }
                break;
        }
        adapter.notifyDataSetChanged();
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

