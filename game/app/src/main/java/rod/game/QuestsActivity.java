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
public class QuestsActivity extends Activity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_quests);

        //Ready Buttons
        ImageButton goOnQuest = (ImageButton) findViewById(R.id.Quest1);
        goOnQuest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.Quest1:
                //TODO create quest/quest interfaces
                Toast.makeText(QuestsActivity.this, "On your way...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
