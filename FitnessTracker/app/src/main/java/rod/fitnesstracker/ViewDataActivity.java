package rod.fitnesstracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ViewDataActivity extends AppCompatActivity
{
    private DataManager datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        datasource = new DataManager(this);
        datasource.open();

        ListView list = (ListView) findViewById(R.id.list);
        List<Data> values = datasource.getAllData();
        ArrayAdapter<Data> adapter = new ArrayAdapter<Data>(this, android.R.layout.simple_list_item_1, values);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Data dta = (Data)parent.getAdapter().getItem(position);
                Intent myIntent = new Intent(ViewDataActivity.this, TestActivity.class);
                myIntent.putExtra("data", dta.toString());
                startActivity(myIntent);
            }
        });

        //Setup toolbar/actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void onClick(View view)
    {
        @SuppressWarnings("unchecked")
        ListView list = (ListView) findViewById(R.id.list);
        ArrayAdapter<Data> adapter = (ArrayAdapter<Data>) list.getAdapter();
        Data dta = null;

        switch (view.getId())
        {
            //TODO: Implement edit entry functionality.

            case R.id.deleteFirst:
                if (adapter.getCount() > 0)
                {
                    dta = (Data) adapter.getItem(0);
                    datasource.deleteData(dta);
                    adapter.remove(dta);
                    Toast.makeText(this, "Item deleted!", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(this, "No item to delete!", Toast.LENGTH_SHORT).show();

                break;
            case R.id.deleteLast:
                int ct = adapter.getCount();
                if (ct > 0)
                {
                    dta = (Data) adapter.getItem(ct - 1);
                    datasource.deleteData(dta);
                    adapter.remove(dta);
                    Toast.makeText(this, "Item deleted!", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(this, "No item to delete!", Toast.LENGTH_SHORT).show();

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle presses on the action bar items
        switch (item.getItemId())
        {
            case R.id.action_add_data:
                startActivity(new Intent(ViewDataActivity.this, InternalDBActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.view_data_activity_menu, menu);
        return true;
    }
}
