package se.mah.kingdom;

import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EventManager extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		setContentView(R.layout.activity_event_manager);

		
	};

	public void onStart() {
		super.onStart();
	}

	public void onDestroy() {
		super.onDestroy();
	}

	public void onPause() {
		super.onPause();
	}

	public void onStop() {
		super.onStop();

	}
	@Override
	public void onBackPressed() {
		Toast.makeText(this, "no back", Toast.LENGTH_SHORT).show();
		super.onBackPressed();
	}
}
