package com.rod.rodsgame;


import chars.Equipment;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class AddCharacterActivity extends Activity implements OnClickListener{
	
	String newCharClass = "Classless";
	TypedArray HeadImages;
	TypedArray BodyImages;
	TypedArray FeetImages;
	TypedArray HandImages;
	
	int iHead;
	int iBody;
	int iFeet;
	
	ImageButton theHead;
	ImageButton theBody;
	ImageView theRHand;
	ImageView theLHand;
	ImageButton theFeet; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_character_screen);
		
		Button AddCharButton = (Button) findViewById(R.id.CharacterCreate);
		AddCharButton.setOnClickListener(this);
		
		theHead = (ImageButton)findViewById(R.id.createHead);		
		theHead.setOnClickListener(this);
		
		theBody = (ImageButton)findViewById(R.id.createBody);
		theBody.setOnClickListener(this);
		
		theRHand = (ImageView)findViewById(R.id.createRHand);
		theLHand = (ImageView)findViewById(R.id.createLHand);
		theLHand.setImageBitmap(Equipment.flip(((BitmapDrawable)theRHand.getDrawable()).getBitmap(), Equipment.FLIP_HORIZONTAL));
		
		theFeet = (ImageButton)findViewById(R.id.createFeet);    	
		theFeet.setOnClickListener(this);
		
		iHead = 0;
		iBody = 0;
		iFeet = 0;			
		
		// Show the Up button in the action bar.
		setupActionBar();
		setUpSpinner();
	}

	private void setUpSpinner() {
		Spinner charSpinner = (Spinner) findViewById(R.id.CharacterClassSpinner);
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.CharacterClassesArray, android.R.layout.simple_spinner_item);
		
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Apply the adapter to the spinner
		charSpinner.setAdapter(adapter);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
		    case R.id.CharacterCreate:
		    	
		    	int check = CharacterContent.Characters.size();
		    	int newCharId = CharacterContent.Characters.size()+1;
		    	
		    	EditText characterName = (EditText) findViewById(R.id.CharacterName); 
		    	String charName = characterName.getText().toString();
		    	Spinner charSpinner = (Spinner)findViewById(R.id.CharacterClassSpinner);
				newCharClass = charSpinner.getSelectedItem().toString();
		    	
		    	CharacterContent.Character newCharacter = new CharacterContent.Character(newCharId,charName,this);
		    	
		    	CharacterContent.addItem( newCharacter );
		    	
		    	newCharacter.equips.helmet.look = ( (BitmapDrawable)theHead.getDrawable()  ).getBitmap();
		    	newCharacter.equips.armor.look 	= ( (BitmapDrawable)theBody.getDrawable()  ).getBitmap();
		    	newCharacter.equips.rGlove.look = ( (BitmapDrawable)theRHand.getDrawable() ).getBitmap();
		    	newCharacter.equips.lGlove.look = ( (BitmapDrawable)theLHand.getDrawable() ).getBitmap();
		    	newCharacter.equips.boots.look 	= ( (BitmapDrawable)theFeet.getDrawable()  ).getBitmap();		    		    	
		    	
				CharacterContent.Character.setCharacterClass(newCharId, newCharClass);					
				
				newCharacter.playable = createCharPlayable();
				
				if (check < CharacterContent.Characters.size()) Toast.makeText(this, "Character Added", Toast.LENGTH_SHORT).show();
				else Toast.makeText(this, "Character Not Added", Toast.LENGTH_SHORT).show();
				
				NavUtils.navigateUpFromSameTask(this);
				
		        break;
		        
		    case R.id.createHead:
		    	
		    	iHead = iHead + 1;		    
		    	
		    	HeadImages = getResources().obtainTypedArray(R.array.heads);		    			    
		    	
		    	if (iHead >= HeadImages.length()) iHead = 0;		    		    			    		  
		    	
		    	theHead.setImageResource(HeadImages.getResourceId(iHead, -1));
		    	
		    	HeadImages.recycle();
		    	
		    	break;
		    	
		    case R.id.createBody:
		    	
		    	iBody = iBody + 1;
		    	
		    	BodyImages = getResources().obtainTypedArray(R.array.bodys);		    	
		    	HandImages = getResources().obtainTypedArray(R.array.hands);
		    	
		    	//Toast.makeText(this, "HeadImages array size = " +  BodyImages.length(), Toast.LENGTH_SHORT).show();
		    	
		    	if (iBody >= BodyImages.length()) iBody = 0;		    	
		    	
		    	theBody.setImageResource(BodyImages.getResourceId(iBody, -1));
		    	theRHand.setImageResource(HandImages.getResourceId(iBody,-1));
		    	theLHand.setImageBitmap(Equipment.flip(((BitmapDrawable)theRHand.getDrawable()).getBitmap(), Equipment.FLIP_HORIZONTAL));
		    	
		    	BodyImages.recycle();
		    	HandImages.recycle();
		    	
		    	break;
		    	
		    case R.id.createFeet:
		    	
		    	iFeet = iFeet + 1;
		    	
		    	FeetImages = getResources().obtainTypedArray(R.array.feets);
		    	
		    	if (iFeet >= FeetImages.length()) iFeet = 0;
		    			    	
		    	theFeet.setImageResource(FeetImages.getResourceId(iFeet, -1));
		    	
		    	FeetImages.recycle();
		    	break;
	    }   
		
	}

	private Bitmap createCharPlayable() {
		
		Bitmap[] parts = new Bitmap[5];
		
		parts[0] = ((BitmapDrawable)theHead.getDrawable()).getBitmap();
		parts[1] = ((BitmapDrawable)theLHand.getDrawable()).getBitmap();
		parts[2] = ((BitmapDrawable)theBody.getDrawable()).getBitmap();
		parts[3] = ((BitmapDrawable)theRHand.getDrawable()).getBitmap();
		parts[4] = ((BitmapDrawable)theFeet.getDrawable()).getBitmap();
		
		int w = parts[0].getWidth() * 2;
	    int h =parts[0].getHeight() * 3;
		
	    Bitmap result = Bitmap.createBitmap( w , h, Bitmap.Config.ARGB_8888);
	    
	    Canvas canvas = new Canvas(result);
	    Paint paint = new Paint();
	        
	    canvas.drawBitmap(parts[0], w/4, 0, paint);
	    canvas.drawBitmap(parts[1], 0, h/3, paint);
	    canvas.drawBitmap(parts[2], w/4, h/3, paint);
	    canvas.drawBitmap(parts[3], w/4*3,h/3, paint);
	    canvas.drawBitmap(parts[4], w/4, h/3*2, paint);
	    
	    result = Bitmap.createScaledBitmap(result, 90, 90, false);    	 	    
	    
	    return result;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) 
		{
			case android.R.id.home:
				// This ID represents the Home or Up button. In the case of this
				// activity, the Up button is shown. Use NavUtils to allow users
				// to navigate up one level in the application structure. For
				// more details, see the Navigation pattern on Android Design:
				//
				// http://developer.android.com/design/patterns/navigation.html#up-vs-back
				//
				NavUtils.navigateUpFromSameTask(this);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public class SpinnerActivity extends Activity implements OnItemSelectedListener {
	    	    
	    public void onItemSelected(AdapterView<?> parent, View view, 
	            int pos, long id) {
	        // An item was selected. You can retrieve the selected item using
	        newCharClass = (String) parent.getItemAtPosition(pos);	    
	    }

	    public void onNothingSelected(AdapterView<?> parent) {
	        // Another interface callback
	    }
	}
		

}


