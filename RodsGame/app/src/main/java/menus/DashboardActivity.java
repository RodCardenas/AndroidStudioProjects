package menus;

import com.rod.rodsgame.AddCharacterActivity;
import com.rod.rodsgame.CharacterContent;
import com.rod.rodsgame.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class DashboardActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Remove title bar
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_dashboard);
		
		Button start = (Button) findViewById(R.id.startB);
        start.setOnClickListener(this);
        
        Button settings = (Button) findViewById(R.id.settingB);
        settings.setOnClickListener(this); 
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
			case R.id.startB:
							
				if ( CharacterContent.Characters.isEmpty())
				{
					Intent goToCreateCharacter = new Intent(DashboardActivity.this, AddCharacterActivity.class);
		        	startActivity(goToCreateCharacter); 	
		        	break;
				}
				
				else
				{
				
					Intent goToMainMenu = new Intent(DashboardActivity.this, MainMenuActivity.class);													
				    startActivity(goToMainMenu);
				}        break;
		        
			case R.id.settingB:
				Intent goToPreferences = new Intent(DashboardActivity.this, SettingsActivity.class);
		        startActivity(goToPreferences); 
		        break;	        
		}
		
	}	
}
