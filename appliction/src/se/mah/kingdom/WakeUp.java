package se.mah.kingdom;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.view.View;

public class WakeUp extends Activity {
	private MediaPlayer event_player;
	private AudioManager event_am;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wake_up);
		event_am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		event_am.setMode(AudioManager.MODE_NORMAL);
		event_am.setSpeakerphoneOn(true);
		event_am.setBluetoothScoOn(false);
		event_player = MediaPlayer.create(this,
				Settings.System.DEFAULT_RINGTONE_URI);
		event_player.setAudioStreamType(AudioManager.STREAM_MUSIC);
		event_player.start();
	}

	public void onStart() {
		event_player.start();
		super.onStart();
	}

	public void onDestroy() {
		event_player.stop(); 
		event_player.release();
		super.onDestroy();
	}

	public void onPause() {
		event_player.pause();
		super.onPause();
	}

	public void phone_button(View v) {
		event_player.stop();
		event_am.setMode(AudioManager.MODE_IN_CALL);
		event_am.setSpeakerphoneOn(false);
		event_am.setBluetoothScoOn(true);
		event_player = MediaPlayer.create(WakeUp.this, R.raw.event_voice);
		event_player.start();
	}
}