package com.example.tyres;

import com.parse.Parse;

import android.app.Application;
import android.util.Log;

public class TyresApp extends Application {
	
	private static TyresApp myTyresApp = null;
	
	
	public TyresApp(){
		super();
		myTyresApp = this;
	}
	
	
	public static TyresApp getInstance(){
		if(myTyresApp == null){
			myTyresApp = new TyresApp();
		}
		return myTyresApp;
	}
	
	public void onCreate(){
		super.onCreate();
		Log.e("singleton","called");
		Parse.initialize(this, "M7YADrICOFex90xVX3O3Mbv4vtJ3GvEUg7YRYF8y", "7ah39WzDy3ohSeBaSA672WCIi6XGs7DKpvly0KEc");

	}

}
