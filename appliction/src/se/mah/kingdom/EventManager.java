package se.mah.kingdom;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class EventManager extends Activity {
	private MediaPlayer event_player;
	private AudioManager event_am;
	private ResourcesKingdom resourcePrefs;

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
		Resources res=this.getResources();
		musicfileid = getResources().getIdentifier("event_voice", "raw", "se.mah.kingdom");
		event_player = MediaPlayer.create(this, musicfileid);
		event_player.start(); 
		resourcePrefs = new ResourcesKingdom(getApplicationContext()); 

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
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
	}
	public void onStop(){
		super.onStop();
		
	}
	
	public void btnSound (View v){
		Toast.makeText(this, "music set", Toast.LENGTH_SHORT).show();
		if (event_am.getMode()==AudioManager.MODE_IN_CALL){
			event_am.setMode(AudioManager.MODE_NORMAL);}
		else{
			event_player.pause();
			event_am.setMode(AudioManager.MODE_IN_CALL);
			event_player.start();
		}
	}
	
	public void btnStop (View v){
		Toast.makeText(this, "music stop", Toast.LENGTH_SHORT).show();
		if(event_player.isPlaying()){
			event_player.pause();}
		else{
			event_player.start();
		}
	}
	
	public void btnReplay (View v){
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

		Toast.makeText(this,"food "+ Integer.toString(food), Toast.LENGTH_SHORT).show();

	}
	public void option3_button(View v) {

		ResourcesKingdom.setGoldChange(-5);
		Toast.makeText(this, "gold yes", Toast.LENGTH_SHORT).show();
	}

	public void option4_button(View v) {
		int gold = ResourcesKingdom.getGold();

		Toast.makeText(this, "gold "+Integer.toString(gold), Toast.LENGTH_SHORT).show();

	}
	public void option5_button(View v) {

		ResourcesKingdom.setHappyChange(-5);
		Toast.makeText(this, "happy yes", Toast.LENGTH_SHORT).show();
	}

	public void option6_button(View v) {
		int happy = ResourcesKingdom.getHappy();

		Toast.makeText(this, "happy "+Integer.toString(happy), Toast.LENGTH_SHORT).show();

	}
	public void option7_button(View v) {
		ResourcesKingdom.resetGame();

		Toast.makeText(this, "game over ", Toast.LENGTH_SHORT).show();

	}
	public void option8_button(View v) {
		ResourcesKingdom.setstateGame();
		

		Toast.makeText(this, "game "+ResourcesKingdom.getstateGame(), Toast.LENGTH_SHORT).show();

	}


	@Override
	public void onBackPressed() {
		Toast.makeText(this, "no back", Toast.LENGTH_SHORT).show();
	}

}
