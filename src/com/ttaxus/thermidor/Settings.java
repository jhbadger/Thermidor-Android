package com.ttaxus.thermidor;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.RadioGroup;

public class Settings extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		RadioGroup group = (RadioGroup) findViewById(R.id.radioMonthNames);
		group.check(((Thermidor)getApplication()).getChecked());
			group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					int id;
			        switch(checkedId) {
			        case R.id.radioFrench: 
			        	id = R.array.French_months;
			        	break;
			        case  R.id.radioCarlyle:
			        	id = R.array.Carlyle_months;
			        	break;
			        case R.id.radioSatire:
			        	id = R.array.English_satirical_months;
			        	break;
			        default:
			        	id = R.array.French_months;
			       }
			        ((Thermidor)getApplication()).setMonths(getResources().getStringArray(id));
			        ((Thermidor)getApplication()).setChecked(checkedId);
				}});
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
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

}
