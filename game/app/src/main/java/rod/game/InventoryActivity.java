package rod.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Rodrigo on 11/19/2015.
 */
public class InventoryActivity extends Activity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_inventory);

        //Ready Buttons
        ImageButton inventoryButton0 = (ImageButton) findViewById(R.id.inventoryButton0);
        ImageButton inventoryButton1 = (ImageButton) findViewById(R.id.inventoryButton1);
        ImageButton inventoryButton2 = (ImageButton) findViewById(R.id.inventoryButton2);
        ImageButton inventoryButton3 = (ImageButton) findViewById(R.id.inventoryButton3);
        ImageButton inventoryButton4 = (ImageButton) findViewById(R.id.inventoryButton4);
        ImageButton inventoryButton5 = (ImageButton) findViewById(R.id.inventoryButton5);
        ImageButton inventoryButton6 = (ImageButton) findViewById(R.id.inventoryButton6);
        ImageButton inventoryButton7 = (ImageButton) findViewById(R.id.inventoryButton7);

        inventoryButton0.setOnClickListener(this);
        inventoryButton1.setOnClickListener(this);
        inventoryButton2.setOnClickListener(this);
        inventoryButton3.setOnClickListener(this);
        inventoryButton4.setOnClickListener(this);
        inventoryButton5.setOnClickListener(this);
        inventoryButton6.setOnClickListener(this);
        inventoryButton7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.inventoryButton0:
                Toast.makeText(InventoryActivity.this, "Inventory position 0 is empty...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.inventoryButton1:
                Toast.makeText(InventoryActivity.this, "Inventory position 1 is empty...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.inventoryButton2:
                Toast.makeText(InventoryActivity.this, "Inventory position 2 is empty...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.inventoryButton3:
                Toast.makeText(InventoryActivity.this, "Inventory position 3 is empty...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.inventoryButton4:
                Toast.makeText(InventoryActivity.this, "Inventory position 4 is empty...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.inventoryButton5:
                Toast.makeText(InventoryActivity.this, "Inventory position 5 is empty...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.inventoryButton6:
                Toast.makeText(InventoryActivity.this, "Inventory position 6 is empty...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.inventoryButton7:
                Toast.makeText(InventoryActivity.this, "Inventory position 7 is empty...", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
