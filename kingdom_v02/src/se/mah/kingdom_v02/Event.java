package se.mah.kingdom_v02;

import java.util.Random;
import java.util.Timer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Event extends Activity {
	private MediaPlayer event_player;
	private AudioManager event_am;
	private static PowerManager.WakeLock wakeLock;
	public String event = null;
	public String resourceEffect1 = null;
	public boolean beenPaused = true;
	private ResourcesKingdom resourcePrefs;
	private String character = "thorp";
	private Handler h;
	private Runnable runnable = new Runnable() {
		public void run() {
			decline();
			Intent intent = new Intent(Event.this, StoryManager.class);
			intent.putExtra("event", event);
			Event.this.finish();
			startActivity(intent);// Actions to do after 5 seconds
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wake_up);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		wakeDevice();
		beenPaused = false;

		event_am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		event_am.setMode(AudioManager.MODE_NORMAL);
		event_am.setSpeakerphoneOn(true);
		event_am.setBluetoothScoOn(false);
		event_player = MediaPlayer.create(this,
				Settings.System.DEFAULT_RINGTONE_URI);
		event_player.setAudioStreamType(AudioManager.STREAM_MUSIC);
		event_player.start();

		resourcePrefs = new ResourcesKingdom(Event.this.getApplicationContext());
		Resources resStory = getResources();
		String[] eventArrey = resStory.getStringArray(R.array.event_picker);
		Random r = new Random();
		int min = 0;
		int max = eventArrey.length;
		int i1 = r.nextInt(max - min) + min;
		Log.i("id ", Integer.toString(i1));
		event = eventArrey[i1];
		String eventName = null;
		String eventOption1 = null;
		String[] optionSplit = event.split("/");
		eventName = optionSplit[0];

		eventOption1 = optionSplit[2];
		String[] eventNames = eventName.split("Å");
		ResourcesKingdom.setEventName(eventNames[0]);
		String eventChar = eventNames[1];

		ImageView minister = (ImageView) findViewById(R.id.image_minister);
		TextView minister_first_name = (TextView) findViewById(R.id.text_minister_name_1);
		TextView minister_last_name = (TextView) findViewById(R.id.text_minister_name_2);

		Log.i("Event is ", event);

		if (eventChar.equals("c1")) {
			Log.i("Char is ", "The Chancellor");
			minister.setImageResource(R.drawable.ic_char_laurent);
			minister_first_name.setText("LADY LAURENT");
			minister_last_name.setText("FORTESCUE");
			character = "fortescue";
		}
		if (eventChar.equals("c2")) {
			Log.i("Char is ", "The Minister of Finance");
			minister.setImageResource(R.drawable.ic_char_krane);
			minister_first_name.setText("DUKE LUDWIG von");
			minister_last_name.setText("KRANE");
			character = "krane";
		}
		if (eventChar.equals("c3")) {
			Log.i("Char is ", "The Minister of Defence");
			minister.setImageResource(R.drawable.ic_char_brion);
			minister_first_name.setText("SER GODFRIED");
			minister_last_name.setText("BRION");
			character = "brion";
		}
		if (eventChar.equals("c4")) {
			Log.i("Char is ", "The Minister of Agriculture");
			minister.setImageResource(R.drawable.ic_char_agriculture);
			minister_first_name.setText("BARON BARTHOLOMEW");
			minister_last_name.setText("THORP");
			character = "thorp";
		}
		String[] optionEffect1 = eventOption1.split("¤");
		resourceEffect1 = optionEffect1[1];
		beenPaused = false;
		
		h = new Handler();
		h.postDelayed(runnable, 30000);
//		h.postDelayed(new Runnable() {
//			public void run() {
//				decline();
//				Intent intent = new Intent(Event.this, StoryManager.class);
//				intent.putExtra("event", event);
//				Event.this.finish();
//				startActivity(intent);// Actions to do after 5 seconds
//			}
//		}, 30000);

	}

	public void decline() {
		int resourceNumber = 0;
		ResourcesKingdom.setEventOption(1);
		if (resourceEffect1.length() <= 8) {
			String[] resourceEffect = resourceEffect1.split("#");

			if (resourceEffect[0].equals("gold")) {
				Log.i("resourceEffect", resourceEffect[0] + ""
						+ resourceEffect[1]);

				resourceNumber = Integer.parseInt(resourceEffect[1]);

				ResourcesKingdom.setGoldChange(resourceNumber);
			}
			if (resourceEffect[0].equals("happ")) {
				Log.i("resourceEffect", resourceEffect[0] + ""
						+ resourceEffect[1]);
				resourceNumber = Integer.parseInt(resourceEffect[1]);

				ResourcesKingdom.setHappyChange(resourceNumber);
			}
			if (resourceEffect[0].equals("food")) {
				Log.i("resourceEffect", resourceEffect[0] + ""
						+ resourceEffect[1]);
				resourceNumber = Integer.parseInt(resourceEffect[1]);

				ResourcesKingdom.setFoodChange(resourceNumber);
			} else {
				Log.i("error", resourceEffect[0] + "" + resourceEffect[1]);
			}
		} else {
			String[] resourceEffect = resourceEffect1.split("Ö");
			String[] resourceEffectOne = resourceEffect[0].split("#");
			String[] resourceEffectTwo = resourceEffect[1].split("#");

			if (resourceEffectOne[0].equals("gold")) {
				Log.i("resourceEffectOne", resourceEffectOne[0] + ""
						+ resourceEffectOne[1]);
				resourceNumber = Integer.parseInt(resourceEffectOne[1]);

				ResourcesKingdom.setGoldChange(resourceNumber);
			}
			if (resourceEffectOne[0].equals("happ")) {
				Log.i("resourceEffectOne", resourceEffectOne[0] + ""
						+ resourceEffectOne[1]);
				resourceNumber = Integer.parseInt(resourceEffectOne[1]);

				ResourcesKingdom.setHappyChange(resourceNumber);
			}
			if (resourceEffectOne[0].equals("food")) {
				Log.i("resourceEffectOne", resourceEffectOne[0] + ""
						+ resourceEffectOne[1]);
				resourceNumber = Integer.parseInt(resourceEffectOne[1]);

				ResourcesKingdom.setFoodChange(resourceNumber);
			}
			if (resourceEffectTwo.equals("gold")) {
				Log.i("resourceEffectTwo", resourceEffectTwo[0] + ""
						+ resourceEffectTwo[1]);
				resourceNumber = Integer.parseInt(resourceEffectTwo[1]);

				ResourcesKingdom.setGoldChange(resourceNumber);
			}
			if (resourceEffectTwo.equals("happ")) {
				Log.i("resourceEffectTwo", resourceEffectTwo[0] + ""
						+ resourceEffectTwo[1]);
				resourceNumber = Integer.parseInt(resourceEffectTwo[1]);

				ResourcesKingdom.setHappyChange(resourceNumber);
			}
			if (resourceEffectTwo.equals("food")) {
				Log.i("resourceEffectTwo", resourceEffectTwo[0] + ""
						+ resourceEffectTwo[1]);
				resourceNumber = Integer.parseInt(resourceEffectTwo[1]);

				ResourcesKingdom.setFoodChange(resourceNumber);
			} else {
				Log.i("error", resourceEffect[0] + "" + resourceEffect[1]);
			}
		}

	}

	public void wakeDevice() {
		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager
				.newWakeLock(
						(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
								| PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP),
						"TAG");
		wakeLock.acquire();
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
						| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
						| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	}

	@Override
	public void onStart() {
		event_player.start();
		super.onStart();
	}

	@Override
	public void onDestroy() {
		getWindow()
				.clearFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		event_player.release();
		decline();
		super.onDestroy();

	}

	@Override
	public void onResume() {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

		if (beenPaused == true) {

			Intent intent = new Intent(Event.this, StoryManager.class);

			Event.this.finish();
			startActivity(intent);
		}

		super.onResume();

	}

	@Override
	public void onPause() {
		getWindow()
				.clearFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		decline();
		beenPaused = true;
		event_player.stop();
		super.onPause();

	}

	public void phone_button(View v) {
		h.removeCallbacks(runnable);
		event_player.stop();
		int musicfileid;
		event_am = (AudioManager) Event.this
				.getSystemService(Context.AUDIO_SERVICE);
		event_am.setMode(AudioManager.MODE_IN_CALL);
		event_am.setSpeakerphoneOn(false);
		event_am.setBluetoothScoOn(true);
		Resources res = this.getResources();
		musicfileid = getResources().getIdentifier(character, "raw",
				"se.mah.kingdom_v02");
		event_player = MediaPlayer.create(Event.this, musicfileid);
		event_player.start();
		ImageView Bi = (ImageView) findViewById(R.id.btn_phone_accept);
		Bi.setVisibility(View.GONE);
		ImageView Bc = (ImageView) findViewById(R.id.btn_phone_decline);
		Bc.setVisibility(View.GONE);
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				Intent intent = new Intent(Event.this, EventManager.class);
				intent.putExtra("event", event);
				Event.this.finish();
				startActivity(intent);// Actions to do after 5 seconds
			}
		}, 5000);

	}

	public void btnDecline(View v) {
		h.removeCallbacks(runnable);
		decline();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);

	}

}