package com.example.tyres;

import java.util.ArrayList;
import java.util.List;

import com.parse.*;
import com.example.tyres.R;
import com.example.tyres.R.id;
import com.example.tyres.R.layout;
import com.example.tyres.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.support.v4.app.NavUtils;

public class CompatibleTyres extends Activity {

	Spinner spinner_prod, spinner_model, spinner_tag;

//	private Set<String> prod = new 
	
	private ArrayList<String> producers = new ArrayList<String>();//car producers must be unique , use a SET
	private String[] models= {"Logan", "Sandero", "Duster", "ML350", "Golf V", "Passat", "Bora", "Phantom"};
	private String[] tags= {"Viteza","Offroad"};
	
	private String selectedProducer = new String(spinner_prod.getSelectedItem().toString());
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("compatibleTyres","on create called");
		setContentView(R.layout.activity_compatible_tyres);
		// Show the Up button in the action bar.
		Log.d("compatibleTyres","cont view set");
			
//		ParseQuery<ParseObject> query = ParseQuery.getQuery("Automobile");
//		query.whereNotEqualTo("Producator", "");
//		query.findInBackground(new FindCallback<ParseObject>() {
//		    public void done(List<ParseObject> carList, ParseException e) {
//		        if (e == null) {
//		            Log.d("getCars","Success");
//		            for(ParseObject p : carList)
//		            	producers.add(p.getString("Producator"));
//		        } else {
//		            Log.d("getCars", "Error: " + e.getMessage());
//		        }
//		    }
//		});
		
		setupActionBar();
		setupSpinners();

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
		getMenuInflater().inflate(R.menu.compatible_tyres, menu);
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

	private void setupSpinners() {

		spinner_prod = (Spinner) findViewById(R.id.spinnerProd_compatible);
		spinner_model = (Spinner) findViewById(R.id.spinnerModel_compatible);
		spinner_tag=(Spinner) findViewById(R.id.spinnerTag_compatible);
		
		ArrayAdapter<String> prod_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, producers);
		ArrayAdapter<String> model_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, models);
		ArrayAdapter<String> tag_adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, tags);


		spinner_prod.setAdapter(prod_adapter);
		spinner_model.setAdapter(model_adapter);
		spinner_tag.setAdapter(tag_adapter);

	}

}
