package com.example.tyres;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button tyre_selector, tyre_info, tyre_help, tyre_compatible;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final MainActivity sActiv = this;
		tyre_selector = (Button) findViewById(R.id.btnSelector);
		tyre_info = (Button) findViewById(R.id.btnInfo);
		tyre_help = (Button) findViewById(R.id.btnHelp);
		tyre_compatible = (Button) findViewById(R.id.btnComp);

		tyre_selector.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent openActivity = new Intent(sActiv,TyreSelector.class);
				startActivity(openActivity);

			}
		});

		tyre_info.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent openActivity = new Intent(
						"com.tyres4all.aplicatie.MyTyreInfo");
				startActivity(openActivity);

			}
		});
		
		tyre_help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent openActivity = new Intent(
						"com.tyres4all.aplicatie.HelpActivity");
				startActivity(openActivity);

			}
		});
		
		tyre_compatible.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("mainActiv","clicked");
				Intent openActivity = new Intent(sActiv, CompatibleTyres.class);
				startActivity(openActivity);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
