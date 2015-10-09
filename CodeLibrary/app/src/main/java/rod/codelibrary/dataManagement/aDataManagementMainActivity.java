package rod.codelibrary.dataManagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import rod.codelibrary.R;


public class aDataManagementMainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_management_main);
    }

    public void addDataInternalFile(View view)
    {
        Intent myIntent = new Intent(aDataManagementMainActivity.this, InternalFileDataManagementActivity.class);
        startActivity(myIntent);
    }

    public void addDataInternalDatabase(View view)
    {
        Intent myIntent = new Intent(aDataManagementMainActivity.this, InternalDatabaseDataManagementActivity.class);
        startActivity(myIntent);
    }
}

