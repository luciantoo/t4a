package com.example.tyres;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.example.tyres.R;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;



public class TyreSelector extends Activity {

	Spinner spinner_prod,spinner_model,spinner_anvelo;
	ImageButton btnOK;
	
	//String arrays in care am introdus producatorii sau modelele; se poate folosi si ArrayList<String>
	private List<ParseObject> compatibleTyresList = new ArrayList<ParseObject>();
	private Set<String> sProducers = new HashSet<String>();
	private Set<String> sModels = new HashSet<String>();
	private Set<String>	sAnvelo = new HashSet<String>();
	private List<String> producers =new ArrayList<String>();
	private List<String> models = new ArrayList<String>();
	private List<String> anvelo = new ArrayList<String>();
//	private String[] tags= {"Viteza","Offroad"};
	
	String marca,model,tyre;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tyre_selector);
		btnOK=(ImageButton) findViewById(R.id.btnOK);
		btnOK.setBackgroundColor(Color.TRANSPARENT);
		// Show the Up button in the action bar
		
		if(!isOnline()){
			Toast.makeText(getApplicationContext(), "Please activate internet connection", Toast.LENGTH_SHORT).show();
		}

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Automobile");
		query.whereNotEqualTo("Producator", "");
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> mixedList, ParseException e) {
		        if (e == null) {
		            Log.d("getCars","Success");
		            String s;
		            for(ParseObject p : mixedList){
		            	s = new String( p.getString("Producator").toString());
		            	producers.add((String)s.toString());
		            }
		            sProducers.addAll(producers);
		    		Log.d("sProducers",sProducers.toString());
		    		producers.clear();
		    		producers.addAll(sProducers);
		    		Log.d("sProducers",producers.toString());
		    		
		
		
//		setupActionBar();
		setupProdSpinner();
		
		
		spinner_prod.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
            	
                marca=spinner_prod.getSelectedItem().toString();
                //clear the car model from the producer selected previously
                sModels.clear();
                models.clear();
                anvelo.clear();
                compatibleTyresList.clear();
                getModelsFromParse(marca);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
		});
		
		
		
		        } else {
		            Log.d("getCars", "Error: " + e.getMessage());
		            
		        }
		    }
		});
		
}
	
	@Override
	public void onResume(){
		super.onResume();
		
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}
	
//	public void onWindowFocusChanged (boolean hasFocus){
//		super.onWindowFocusChanged(hasFocus);
//		View decorView = getWindow().getDecorView();
//		// Hide the status bar.
//		int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
//		decorView.setSystemUiVisibility(uiOptions);
//		// Remember that you should never show the action bar if the
//		// status bar is hidden, so hide that too if necessary.
//		ActionBar actionBar = getActionBar();
//		actionBar.hide();
//	}
	

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
//	private void setupActionBar() {
//
//		getActionBar().setDisplayHomeAsUpEnabled(false);
//
//	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.tyre_selector, menu);
//		return true;
//	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			// This ID represents the Home or Up button. In the case of this
//			// activity, the Up button is shown. Use NavUtils to allow users
//			// to navigate up one level in the application structure. For
//			// more details, see the Navigation pattern on Android Design:
//			//
//			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
//			//
//			NavUtils.navigateUpFromSameTask(this);
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
	
	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	private void setupProdSpinner() {
		spinner_prod=(Spinner) findViewById(R.id.spinnerProd);
		ArrayAdapter<String> prod_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,producers);
		spinner_prod.setAdapter(prod_adapter);
		
		
	}
	private void setupModelSpinner(){
		spinner_model=(Spinner) findViewById(R.id.spinnerModel);
		ArrayAdapter<String> model_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,models);
		spinner_model.setAdapter(model_adapter);
		
	}
	
	private void setupAnvelopeSpinner(){
		spinner_anvelo=(Spinner) findViewById(R.id.spinnerAnvelope);
		ArrayAdapter<String> anvelo_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,anvelo);
		spinner_anvelo.setAdapter(anvelo_adapter);
		
	}
	
	public void getModelsFromParse(String producer){
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Automobile");
		query.whereEqualTo("Producator", producer);
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> mixedList, ParseException e) {
		        if (e == null) {
		            Log.d("getCars","Success");
		            String s;
		            for(ParseObject p : mixedList){
		            	s = new String( p.getString("Model").toString());
		            	models.add((String)s.toString());
		            }
		            sModels.addAll(models);
		    		Log.d("sModels",sModels.toString());
		    		models.clear();
		    		models.addAll(sModels);
		    		Log.d("Models",models.toString());
		    		
		    		setupModelSpinner();
		    		spinner_model.setOnItemSelectedListener(new OnItemSelectedListener() {

		                @Override
		                public void onItemSelected(AdapterView<?> arg0, View arg1,
		                        int arg2, long arg3) {
		                    // TODO Auto-generated method stub
		                    model=spinner_model.getSelectedItem().toString();
		                    anvelo.clear();
		                    compatibleTyresList.clear();
		                    Log.d("a model is selected"," will get tyres");
		                    getAnvelopeFromParse(model);

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
		    		
		    		
		        }else {
			            Log.d("getCars", "Error: " + e.getMessage());
			        }
			    }
			});
	}
	
	public void getAnvelopeFromParse(String selectedModel){
		Log.d("getAnvelopeFromParse","method entered");
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Tyres");
		query.whereEqualTo("compatibleWith", selectedModel);
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> mixedList, ParseException e) {
		        if (e == null) {
		            Log.d("getTyresForSelectedModel","Success");
		            compatibleTyresList.addAll(mixedList);
		            String s;
		            for(ParseObject p : mixedList){
		            	s = new String( p.getNumber("width").toString() +"/"
		            + p.getNumber("ratio").toString() + "R" + p.getNumber("radius").toString()
		            + " " + p.getNumber("loadIndex").toString() + p.getString("speedIndex")); // take every attribute of a tyre
		            	anvelo.add((String)s.toString());
		            }
		            sAnvelo.addAll(anvelo);
		    		Log.d("sAnvelo",sAnvelo.toString());
		    		anvelo.clear();
		    		anvelo.addAll(sAnvelo);
		    		Log.d("anvelo",anvelo.toString());
		    		
		    		setupAnvelopeSpinner();
		    		spinner_anvelo.setOnItemSelectedListener(new OnItemSelectedListener() {

		                @Override
		                public void onItemSelected(AdapterView<?> arg0, View arg1,
		                        int arg2, long arg3) {
		                    // TODO Auto-generated method stub
		                    tyre=spinner_model.getSelectedItem().toString();
		                    

		                }

		                @Override
		                public void onNothingSelected(AdapterView<?> arg0) {
		                    // TODO Auto-generated method stub
		                }
		    		});
		    		
		    		
		        }else {
			            Log.d("getAnvelope", "Error: " + e.getMessage());
			        }
			    }
			});
	}
	
	
	
	
}
