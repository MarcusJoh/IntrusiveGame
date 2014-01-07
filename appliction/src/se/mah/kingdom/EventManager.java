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
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EventManager extends Activity {
	private MediaPlayer event_player;
	private AudioManager event_am;
	private Resources resourcePrefs;
	private Timer timer;
	private int update;
	private int timesHappend;
	private Calendar time;
	private double increaseInterval;
	private int increaseRate;
	private double totalTimeElapsed;
	private int likenessOfEmergency;
	private boolean emergencyHappening;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_manager);
		event_am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		event_am.setMode(AudioManager.MODE_IN_CALL);
		event_am.setSpeakerphoneOn(false);
		event_am.setBluetoothScoOn(true);
		event_player = MediaPlayer.create(this, R.raw.event_voice);
		event_player.start();
		resourcePrefs = new Resources(getApplicationContext());
		timer = new Timer();
		time = Calendar.getInstance();
		update = 1;
		increaseInterval = 1 * 60;
		timesHappend = 0;
		increaseRate = 1;
		totalTimeElapsed = 0;
		likenessOfEmergency = 1;
		emergencyHappening = false;

	};

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

	public void option1_button(View v) {

		Resources.setFoodChange(-5);
		Toast.makeText(this, "food yes", Toast.LENGTH_SHORT).show();
	}

	public void option2_button(View v) {
		int food = Resources.getFood();

		Toast.makeText(this, "food " + Integer.toString(food),
				Toast.LENGTH_SHORT).show();

	}

	public void option3_button(View v) {

		Resources.setGoldChange(-5);
		Toast.makeText(this, "gold yes", Toast.LENGTH_SHORT).show();
	};

	public void option4_button(View v) {
		int gold = Resources.getGold();

		Toast.makeText(this, "gold " + Integer.toString(gold),
				Toast.LENGTH_SHORT).show();

	}

	public void option5_button(View v) {

		Resources.setHappyChange(-5);
		Toast.makeText(this, "happy yes", Toast.LENGTH_SHORT).show();
	}

	public void option6_button(View v) {
		int happy = Resources.getHappy();

		Toast.makeText(this, "happy " + Integer.toString(happy),
				Toast.LENGTH_SHORT).show();

	}

	public void option7_button(View v) {
		Resources.resetGame();

		Toast.makeText(this, "game over ", Toast.LENGTH_SHORT).show();

	}

	public void option8_button(View v) {
		Resources.setstateGame();

		Toast.makeText(this, "game " + Resources.getstateGame(),
				Toast.LENGTH_SHORT).show();

	}

	public void SomeEmergency() {
		Toast.makeText(this, "Lord the kingdom is on fire, open the dam!", Toast.LENGTH_SHORT).show();
		emergencyHappening = true;
	}

	public boolean UpdateRate_Increase() {
		if (totalTimeElapsed >= increaseInterval) {
			increaseRate++;
			totalTimeElapsed = 0;
			Toast.makeText(this, "increaseRate is up ", Toast.LENGTH_LONG)
					.show();
			return true;
		}
		return false;
	}


	public void timer_button(View v) {

	}

	@Override
	public void onBackPressed() {
		Toast.makeText(this, "no back", Toast.LENGTH_SHORT).show();
	}

}
