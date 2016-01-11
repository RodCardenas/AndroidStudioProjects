package com.rod.rodsgame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A fragment representing a single Character detail screen. This fragment is
 * either contained in a {@link CharacterListActivity} in two-pane mode (on
 * tablets) or a {@link CharacterDetailActivity} on handsets.
 */
public class CharacterDetailFragment extends Fragment {

	public static String ActiveChar = "charId";

	private CharacterContent.Character aChar;

	public CharacterDetailFragment() {
		/**
		 * Mandatory empty constructor for the fragment manager to instantiate the
		 * fragment (e.g. upon screen orientation changes).
		 */
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ActiveChar)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			int charId = getArguments().getInt(ActiveChar);
			aChar = CharacterContent.Characters.get(charId);
		}
	}

	private void setNames(View v) {
		
		((TextView) v.findViewById((R.id.cName))).setText(aChar.name);
		
		((TextView) v.findViewById((R.id.cLevel))).setText("Level:" + aChar.level);
		
		((TextView) v.findViewById((R.id.cHP))).setText("HP:   " + aChar.hp);
		
		((TextView) v.findViewById((R.id.cStr))).setText("Str:  " + aChar.str);
		
		((TextView) v.findViewById((R.id.cDef))).setText("Def:  " + aChar.def);
		
		((TextView) v.findViewById((R.id.cClass))).setText(aChar.character_class);

		((ImageView) v.findViewById(R.id.cHead)).setImageBitmap(aChar.equips.helmet.look);
		
		((ImageView) v.findViewById(R.id.cBody)).setImageBitmap(aChar.equips.armor.look); 
		
		((ImageView) v.findViewById(R.id.cLHand)).setImageBitmap(aChar.equips.lGlove.look);
		
		((ImageView) v.findViewById(R.id.cRHand)).setImageBitmap(aChar.equips.rGlove.look);
		
		((ImageView) v.findViewById(R.id.cFeet)).setImageBitmap(aChar.equips.boots.look);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{		
		View charDetailView = inflater.inflate(R.layout.fragment_character_detail, container, false);
		
		if (aChar != null) {
			setNames(charDetailView);
		}

		return charDetailView;
	}
}
