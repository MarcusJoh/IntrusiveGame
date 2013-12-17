package se.mah.kingdom;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class EventManager extends Activity {
	private MediaPlayer event_player;
	private AudioManager event_am;
	private Resources resourcePrefs;

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

		Toast.makeText(this,"food "+ Integer.toString(food), Toast.LENGTH_SHORT).show();

	}
	public void option3_button(View v) {

		Resources.setGoldChange(-5);
		Toast.makeText(this, "gold yes", Toast.LENGTH_SHORT).show();
	}

	public void option4_button(View v) {
		int gold = Resources.getGold();

		Toast.makeText(this, "gold "+Integer.toString(gold), Toast.LENGTH_SHORT).show();

	}
	public void option5_button(View v) {

		Resources.setHappyChange(-5);
		Toast.makeText(this, "happy yes", Toast.LENGTH_SHORT).show();
	}

	public void option6_button(View v) {
		int happy = Resources.getHappy();

		Toast.makeText(this, "happy "+Integer.toString(happy), Toast.LENGTH_SHORT).show();

	}
	public void option7_button(View v) {
		Resources.resetGame();

		Toast.makeText(this, "game over ", Toast.LENGTH_SHORT).show();

	}
	public void option8_button(View v) {
		Resources.setstateGame();
		

		Toast.makeText(this, "game "+Resources.getstateGame(), Toast.LENGTH_SHORT).show();

	}


	@Override
	public void onBackPressed() {
		Toast.makeText(this, "no back", Toast.LENGTH_SHORT).show();
	}

}
