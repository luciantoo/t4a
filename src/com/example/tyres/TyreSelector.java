package com.example.tyres;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.example.tyres.R;
import com.example.tyres.R.id;
import com.example.tyres.R.layout;
import com.example.tyres.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;



public class TyreSelector extends Activity {

	Spinner spinner_prod,spinner_model;
	Button btnOK;
	
	//String arrays in care am introdus producatorii sau modelele; se poate folosi si ArrayList<String>
	private ArrayList<String> producers = new ArrayList<String>();
	private String[] models= {"Logan", "Sandero", "Duster", "ML350", "Golf V", "Passat", "Bora", "Phantom"};
	private String[] tags= {"Viteza","Offroad"};
	
	String marca,model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tyre_selector);
		// Show the Up button in the action bar.
//		Parse.initialize(this, "M7YADrICOFex90xVX3O3Mbv4vtJ3GvEUg7YRYF8y", "7ah39WzDy3ohSeBaSA672WCIi6XGs7DKpvly0KEc");
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Automobile");
		query.whereNotEqualTo("Producator", "");
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> carList, ParseException e) {
		        if (e == null) {
		            Log.d("getCars","Success");
		            for(ParseObject p : carList)
		            	producers.add(p.getString("Producator"));
		        } else {
		            Log.d("getCars", "Error: " + e.getMessage());
		        }
		    }
		});
		setupActionBar();
		setupSpinners();
		btnOK=(Button) findViewById(R.id.btnOK);
		
		
		
		spinner_prod.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
            	
                marca=spinner_prod.getSelectedItem().toString();
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
		});
		
		spinner_model.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
                // TODO Auto-generated method stub
                model=spinner_model.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
		});
		
		btnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Toast.makeText(getApplicationContext(), "Masina selectata: "+marca+" "+model, Toast.LENGTH_SHORT).show();
				
			}
		});
		
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
		getMenuInflater().inflate(R.menu.tyre_selector, menu);
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
		
		spinner_prod=(Spinner) findViewById(R.id.spinnerProd);
		spinner_model=(Spinner) findViewById(R.id.spinnerModel);
		ArrayAdapter<String> prod_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,producers);
		ArrayAdapter<String> model_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,models);
		
		spinner_prod.setAdapter(prod_adapter);
		spinner_model.setAdapter(model_adapter);
		
	}
	
}
