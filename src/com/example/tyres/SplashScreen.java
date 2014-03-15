package com.example.tyres;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashScreen extends Activity {
	private Thread mSplashThread; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		// Splash screen view
        setContentView(R.layout.splash);
         
        final SplashScreen sPlashScreen = this;   
        
        final ImageView splashImageView = 
                (ImageView) findViewById(R.id.SplashImageView);
         splashImageView.setBackgroundResource(R.drawable.flag);
         final AnimationDrawable frameAnimation = 
                      (AnimationDrawable)splashImageView.getBackground(); 
         splashImageView.post(new Runnable(){
             @Override
             public void run() {
                 frameAnimation.start();                
             }            
         });
        
        // The thread to wait for splash screen events
        mSplashThread =  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        // Wait given period of time or exit on touch
                        wait(3500);
                    }
                }
                catch(InterruptedException ex){                    
                }
 
                finish();
                 
                // Run next activity
                Intent intent = new Intent();
                intent.setClass(sPlashScreen, MainActivity.class);
                startActivity(intent);
                finish();                    
            }
        };
         
        mSplashThread.start();    
		
	}
	
	

	
	
	
	@Override
	public void onResume(){
		super.onResume();
//		View decorView = getWindow().getDecorView();
//		// Hide the status bar.
//		int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//		decorView.setSystemUiVisibility(uiOptions);
//		// Remember that you should never show the action bar if the
//		// status bar is hidden, so hide that too if necessary.
//		ActionBar actionBar = getActionBar();
//		actionBar.hide();
	}
	
	public void onWindowFocusChanged (boolean hasFocus){
//		View decorView = getWindow().getDecorView();
//		// Hide the status bar.
//		int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//		decorView.setSystemUiVisibility(uiOptions);
//		// Remember that you should never show the action bar if the
//		// status bar is hidden, so hide that too if necessary.
//		ActionBar actionBar = getActionBar();
//		actionBar.hide();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
