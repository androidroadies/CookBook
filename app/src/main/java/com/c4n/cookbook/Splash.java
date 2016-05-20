package com.c4n.cookbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.airpush.android.Airpush;

public class Splash extends Activity {


Airpush airpush;
	private static String TAG = Splash.class.getName();
	private static long SLEEP_TIME = 3; // Sleep for some time

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.splash);
		
		 airpush = new Airpush(this);
			airpush.startSmartWallAd(); 
			
			airpush.startPushNotification(false);
			// start icon ad.
			airpush.startIconAd();
		isNetworkConnected();
		
		// Start timer and launch main activity
		IntentLauncher launcher = new IntentLauncher();
		launcher.start();
	}

	

	  @Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
				// use smart wall on app exit.
				// airpush.startSmartWallAd();
			}
			return super.onKeyDown(keyCode, event);
		}
	private boolean isNetworkConnected() {
		  ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		  NetworkInfo ni = cm.getActiveNetworkInfo();
		  if (ni == null) {
		   // There are no active networks.
		   return false;
		  } else
		   return true;
		 }
	private class IntentLauncher extends Thread {
		@Override
		/**
		 * Sleep for some time and than start new activity.
		 */
		public void run() {
			try {
				// Sleeping
				Thread.sleep(SLEEP_TIME * 1000);
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}

			// Start main activity
			Intent intent = new Intent(Splash.this, TabSample.class);
			Splash.this.startActivity(intent);
			Splash.this.finish();
		}
	}
}