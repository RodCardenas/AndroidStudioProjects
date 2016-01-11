package menus;

import maps.TiledMapsActivity;

import chars.Tools;

import com.rod.rodsgame.CharacterContent;
import com.rod.rodsgame.CharacterListActivity;
import com.rod.rodsgame.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends Activity implements OnClickListener {
	
	public static CharacterContent.Character activePlayer;
	TextView playerName;
	TextView playerLevel;
	ImageView playerPlayable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Remove title bar
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_main_menu);
		
		Button cList = (Button) findViewById(R.id.charactersB);
        cList.setOnClickListener(this);
        
        Button maps = (Button) findViewById(R.id.mapsB);
        maps.setOnClickListener(this);
        
        Button tools = (Button) findViewById(R.id.toolB);
        tools.setOnClickListener(this);
        
        playerName = (TextView)findViewById(R.id.ActPlayerName);
        playerLevel = (TextView)findViewById(R.id.ActPlayerLevel);
        playerPlayable = (ImageView) findViewById(R.id.ActPlayerPlayable);
        
        if(CharacterContent.Characters.size() == 1)
        {
        	activePlayer = CharacterContent.Characters.get(0);        	
	        playerName.setText(String.valueOf(activePlayer.name));	        	        
	        playerLevel.setText(String.valueOf(activePlayer.level));
	        playerPlayable.setImageBitmap(activePlayer.playable);
        }//else TODO prompt User
        
        
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
			case (R.id.charactersB):								
				Intent goToCharList = new Intent(MainMenuActivity.this, CharacterListActivity.class);			
				startActivity(goToCharList);
				break;
				
			case (R.id.mapsB):
				if (activePlayer == null) Toast.makeText(this, "No Active Player", Toast.LENGTH_SHORT).show();
				Intent goToMaps = new Intent(MainMenuActivity.this, TiledMapsActivity.class);											
        		startActivity(goToMaps); 	        			
				break;
			
			case (R.id.toolB):
				Tools Stethoscope = new Tools("Stethoscope", "Awesome piece of equipment.", 130);
				Tools.addTool(Stethoscope);
				if(Tools.toolkit.isEmpty())
				{
					Toast.makeText(this, "Toolkit is " + Tools.toolkit.size() + " items long.", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(this, "Toolkit is " + Tools.toolkit.size() + " items long. The item is called" + Tools.getToolName(((Tools.toolkit.get(0)))), Toast.LENGTH_SHORT).show();
					
				}
				
		
		}
	}

}
