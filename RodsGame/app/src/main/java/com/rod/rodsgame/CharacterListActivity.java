package com.rod.rodsgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * An activity representing a list of Characters. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link CharacterDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link CharacterListFragment} and the item details (if present) is a
 * {@link CharacterDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link CharacterListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class CharacterListActivity extends FragmentActivity implements
		CharacterListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_list);
		setupActionBar();

		if (findViewById(R.id.character_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((CharacterListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.character_list))
					.setActivateOnItemClick(true);
		}
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	

	/**
	 * Callback method from {@link CharacterListFragment.Callbacks} indicating
	 * that the item with the given ID was selected.
	 */
	public static int chosenChar = 1;
	@Override
	public void onItemSelected(int id) {
		chosenChar = id - 1;
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putLong(CharacterDetailFragment.ActiveChar, id);
			CharacterDetailFragment fragment = new CharacterDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.character_detail_container, fragment)
					.commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this,
					CharacterDetailActivity.class);
			detailIntent.putExtra(CharacterDetailFragment.ActiveChar, id);
			startActivity(detailIntent);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(com.rod.rodsgame.R.layout.learning_action_bar, menu);
	   // TextView addCharView = (TextView) menu.findItem(R.id.new_character).getActionView();
	    
	    return super.onCreateOptionsMenu(menu);
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
				NavUtils.navigateUpTo(this, new Intent(this,
						CharacterListActivity.class));			
			return true;
			
			case R.id.new_character:
				Toast.makeText(this, "Add New Character", Toast.LENGTH_SHORT).show();
				Intent addChar = new Intent(this, AddCharacterActivity.class);
				startActivity(addChar);
			return true;

		}
		return super.onOptionsItemSelected(item);
	}
}
