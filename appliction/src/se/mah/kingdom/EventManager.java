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
	private MediaPlayer event_player;
	private AudioManager event_am;

	private ResourcesKingdom resourcePrefs;
	public String optionEvent1 = null;
	public String optionEvent2 = null;
	public String optionEvent3 = null;
	public String optionEvent4 = null;
	public String resourceEffect1 = null;
	public String resourceEffect2 = null;
	public String resourceEffect3 = null;
	public String resourceEffect4 = null;


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
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		setContentView(R.layout.activity_event_manager);
		event_am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		event_am.setMode(AudioManager.MODE_IN_CALL);
		event_am.setSpeakerphoneOn(false);
		event_am.setBluetoothScoOn(true);
		int musicfileid;
		Resources res = this.getResources();
		musicfileid = getResources().getIdentifier("event_voice", "raw",
				"se.mah.kingdom");
		event_player = MediaPlayer.create(this, musicfileid);
		event_player.start();

		resourcePrefs = new ResourcesKingdom(getApplicationContext());

		Resources resStory = getResources();
		String[] eventArrey = resStory.getStringArray(R.array.event_picker);
		Random r = new Random();
		int min = 0;
		int max = eventArrey.length;
		int i1 = r.nextInt(max - min) + min;
		Log.i("id ", Integer.toString(i1));
		String eventid = eventArrey[i1];
		String eventCont = null;
		String eventOption1 = null;
		String eventOption2 = null;
		String eventOption3 = null;
		String eventOption4 = null;
		String eventChain = null;// getting this from resources
		String[] optionSplit = eventid.split("/");
		eventCont = optionSplit[1];
		eventOption1 = optionSplit[2];
		eventOption2 = optionSplit[3];
		eventOption3 = optionSplit[4];
		eventOption4 = optionSplit[5];
		TextView eventtext = (TextView) findViewById(R.id.text_event);
		String[] optionChain = optionSplit[6].split("¤");
		int optionNumber = Integer.parseInt(optionChain[0]);
		if (optionNumber == 2) {
			Log.i("eventArrey", eventCont + " " + eventOption1 + " "
					+ eventOption2);

			eventtext.setText(eventCont);

			String[] optionEffect1 = eventOption1.split("¤");
			String[] optionEffect2 = eventOption2.split("¤");

			Log.i("optionEffect", optionEffect1[0] + "" + optionEffect1[1]);
			optionEvent1 = optionEffect1[0];
			optionEvent2 = optionEffect2[0];

			resourceEffect1 = optionEffect1[1];
			resourceEffect2 = optionEffect2[1];

			eventChain = optionChain[1];
		}
		if (optionNumber == 3) {
			Log.i("eventArrey", eventCont + " " + eventOption1 + " "
					+ eventOption2 + " " + eventOption3);
			eventtext.setText(eventCont);

		timer = new Timer();
		time = Calendar.getInstance();
		update = 1;
		increaseInterval = 1 * 60;
		timesHappend = 0;
		increaseRate = 1;
		totalTimeElapsed = 0;
		likenessOfEmergency = 1;
		emergencyHappening = false;

			String[] optionEffect1 = eventOption1.split("¤");
			String[] optionEffect2 = eventOption2.split("¤");
			String[] optionEffect3 = eventOption3.split("¤");

			Log.i("optionEffect", optionEffect1[0] + "" + optionEffect1[1]);
			optionEvent1 = optionEffect1[0];
			optionEvent2 = optionEffect2[0];
			optionEvent3 = optionEffect3[0];
			resourceEffect1 = optionEffect1[1];
			resourceEffect2 = optionEffect2[1];
			resourceEffect3 = optionEffect3[1];

			eventChain = optionChain[1];
		}
		if (optionNumber == 4) {
			Log.i("eventArrey", eventCont + " " + eventOption1 + " "
					+ eventOption2 + " " + eventOption3 + " " + eventOption4);
			eventtext.setText(eventCont);

			String[] optionEffect1 = eventOption1.split("¤");
			String[] optionEffect2 = eventOption2.split("¤");
			String[] optionEffect3 = eventOption3.split("¤");
			String[] optionEffect4 = eventOption4.split("¤");
			Log.i("optionEffect", optionEffect1[0] + "" + optionEffect1[1]);
			optionEvent1 = optionEffect1[0];
			optionEvent2 = optionEffect2[0];
			optionEvent3 = optionEffect3[0];
			optionEvent4 = optionEffect4[0];
			resourceEffect1 = optionEffect1[1];
			resourceEffect2 = optionEffect2[1];
			resourceEffect3 = optionEffect3[1];
			resourceEffect4 = optionEffect4[1];

			eventChain = optionChain[1];
		}
		if (eventChain != null) {

			if (eventChain == "e1p2") {
				Log.i("chain", "saved " + eventChain);

			}
		}
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
		getWindow()
				.clearFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
	}

	public void onStop() {
		super.onStop();

	}

	public void btnSound(View v) {
		Toast.makeText(this, "music set", Toast.LENGTH_SHORT).show();
		if (event_am.getMode() == AudioManager.MODE_IN_CALL) {
			event_am.setMode(AudioManager.MODE_NORMAL);
		} else {
			event_player.pause();
			event_am.setMode(AudioManager.MODE_IN_CALL);
			event_player.start();
		}
	}

	public void btnOption1(View v) {
		TextView text = (TextView) findViewById(R.id.text_options);

		text.setText(optionEvent1);
	}

	public void btnOption2(View v) {
		TextView text = (TextView) findViewById(R.id.text_options);

		text.setText(optionEvent2);

	}

	public void btnOption3(View v) {
		TextView text = (TextView) findViewById(R.id.text_options);

		text.setText(optionEvent3);

	}

	public void btnOption4(View v) {
		TextView text = (TextView) findViewById(R.id.text_options);

		text.setText(optionEvent4);

	}

	public void btnConfirm(View v) {
		TextView text = (TextView) findViewById(R.id.text_options);
		if (text.getText().toString().equals(optionEvent1)) {
			if (optionEvent1 != null) {
				if (resourceEffect1.length() <= 6) {
					String[] resourceEffect = resourceEffect1.split("#");
					Log.i("resourceEffect", resourceEffect[0] + ""
							+ resourceEffect[1]);
					
				} else {Toast.makeText(this, "option1 selected", Toast.LENGTH_SHORT)
							.show();
				}
			}
		} else if (text.getText().toString().equals(optionEvent2)) {
			if (optionEvent1 != null) {
				String[] resourceEffect = resourceEffect2.split("#");
				Log.i("resourceEffect", resourceEffect[0] + ""
						+ resourceEffect[1]);

				Toast.makeText(this, "option2 selected", Toast.LENGTH_SHORT)
						.show();
			}
		} else if (text.getText().toString().equals(optionEvent3)) {
			if (optionEvent1 != null) {
				String[] resourceEffect = resourceEffect3.split("#");
				Log.i("resourceEffect", resourceEffect[0] + ""
						+ resourceEffect[1]);
			}

			Toast.makeText(this, "option3 selected", Toast.LENGTH_SHORT).show();
		} else if (text.getText().toString().equals(optionEvent4)) {
			if (optionEvent1 != null) {
				String[] resourceEffect = resourceEffect4.split("#");
				Log.i("resourceEffect", resourceEffect[0] + ""
						+ resourceEffect[1]);
			}

			Toast.makeText(this, "option4 selected", Toast.LENGTH_SHORT).show();
		} else {

			Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
		}

	}

	public void btnStop(View v) {
		Toast.makeText(this, "music stop", Toast.LENGTH_SHORT).show();
		if (event_player.isPlaying()) {
			event_player.pause();
		} else {
			event_player.start();
		}
	}

	public void btnReplay(View v) {
		Toast.makeText(this, "music reset", Toast.LENGTH_SHORT).show();
		event_player.stop();
		event_player = MediaPlayer.create(this, R.raw.event_voice);
		event_player.start();
	}

	public void option1_button(View v) {

		ResourcesKingdom.setFoodChange(-5);
		Toast.makeText(this, "food yes", Toast.LENGTH_SHORT).show();
	}

	public void option2_button(View v) {
		int food = ResourcesKingdom.getFood();

		Toast.makeText(this, "food " + Integer.toString(food),
				Toast.LENGTH_SHORT).show();

	}

	public void option3_button(View v) {

		ResourcesKingdom.setGoldChange(-5);
		Toast.makeText(this, "gold yes", Toast.LENGTH_SHORT).show();
	};

	public void option4_button(View v) {
		int gold = ResourcesKingdom.getGold();

		Toast.makeText(this, "gold " + Integer.toString(gold),
				Toast.LENGTH_SHORT).show();

	}

	public void option5_button(View v) {

		ResourcesKingdom.setHappyChange(-5);
		Toast.makeText(this, "happy yes", Toast.LENGTH_SHORT).show();
	}

	public void option6_button(View v) {
		int happy = ResourcesKingdom.getHappy();

		Toast.makeText(this, "happy " + Integer.toString(happy),
				Toast.LENGTH_SHORT).show();

	}

	public void option7_button(View v) {
		ResourcesKingdom.resetGame();

		Toast.makeText(this, "game over ", Toast.LENGTH_SHORT).show();

	}

	public void option8_button(View v) {
		ResourcesKingdom.setstateGame();

		Toast.makeText(this, "game " + ResourcesKingdom.getstateGame(),
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
		//Resources.setstateGame();

	}



	@Override
	public void onBackPressed() {
		Toast.makeText(this, "no back", Toast.LENGTH_SHORT).show();
	}

}
