package maps;

import menus.MainMenuActivity;

import com.rod.rodsgame.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class TiledMapsActivity extends Activity implements OnClickListener {
	
	TextView playerName;
	TextView playerLevel;
	TextView playerHP;
	TextView playerStr;
	TextView playerDef;
	ImageView player;
	
	Button moveB;
	Button attackB;
	Button itemsB;

	FrameLayout frame;
	GridLayout grid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Remove title bar
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_maps);
		
		frame = (FrameLayout) findViewById(R.id.frame);
		grid = (GridLayout)findViewById(R.id.grid);
		
		moveB = (Button) findViewById(R.id.moveB);
        moveB.setOnClickListener(this);
        
        attackB = (Button) findViewById(R.id.attackB);
        attackB.setOnClickListener(this);
        
        itemsB = (Button) findViewById(R.id.itemsB);
        itemsB.setOnClickListener(this);
        
        playerName = (TextView)findViewById(R.id.playerName);
        playerLevel = (TextView)findViewById(R.id.playerLevel);
        playerHP = (TextView) findViewById(R.id.playerHP);
        playerStr = (TextView) findViewById(R.id.playerStr);
        playerDef = (TextView) findViewById(R.id.playerDef);
        
        player = (ImageView) findViewById(R.id.player);        
        
        playerName.setText(String.valueOf(MainMenuActivity.activePlayer.name));
        playerLevel.setText(String.valueOf(MainMenuActivity.activePlayer.level));
        playerHP.setText(String.valueOf(MainMenuActivity.activePlayer.hp));
        playerStr.setText(String.valueOf(MainMenuActivity.activePlayer.str));
        playerDef.setText(String.valueOf(MainMenuActivity.activePlayer.def));
        
        player.setImageBitmap(MainMenuActivity.activePlayer.playable);

	}
	
	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
			case (R.id.moveB):								
				
				break;
				
			case (R.id.attackB):
				 	        			
				break;
				
			case (R.id.itemsB):
				 	        			
				break;
		
		}
	}


}
