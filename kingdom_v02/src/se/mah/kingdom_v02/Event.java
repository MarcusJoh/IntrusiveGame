package se.mah.kingdom_v02;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;

public class Event extends Activity {
	private MediaPlayer event_player;
	private AudioManager event_am;
    private static PowerManager.WakeLock wakeLock;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wake_up);
		wakeDevice();
		event_am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		event_am.setMode(AudioManager.MODE_NORMAL);
		event_am.setSpeakerphoneOn(true);
		event_am.setBluetoothScoOn(false);
		event_player = MediaPlayer.create(this,
				Settings.System.DEFAULT_RINGTONE_URI);
		event_player.setAudioStreamType(AudioManager.STREAM_MUSIC);
		event_player.start();
		
	}
    public void wakeDevice() {
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        wakeLock.acquire();
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
              | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
              | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);   
        
    }
	public void onStart() {
		event_player.start();
		super.onStart();
	}

	public void onDestroy() {
		 
		event_player.release();
		super.onDestroy();
		
		
	}

	public void onPause() {
		 event_player.stop();
		super.onPause();
		
		
	}
	

	public void phone_button(View v) {
		//event_player.stop();
		//event_player.release();
		
		Intent intent = new Intent(Event.this, EventManager.class);
		Event.this.finish();
		
		startActivity(intent);
	}
	
	public void btnDecline (View v){
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		
	}
	

}