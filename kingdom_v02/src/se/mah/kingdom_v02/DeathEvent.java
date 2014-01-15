package se.mah.kingdom_v02;

import java.util.Calendar;
import java.util.Random;

import se.mah.kingdom_v02.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class DeathEvent extends Activity {
	
	TextView deathSentence;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_death_event);
		deathSentence = (TextView) findViewById(id.grave_headline);
		if(ResourcesKingdom.getFood() <=0)
			deathSentence.setText("You starved to death!");
		else if(ResourcesKingdom.getGold() <=0)
			deathSentence.setText("You went bankrupt!");
		else
			deathSentence.setText("You died during a revolt!");
		ResourcesKingdom.resetGame();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.death_event, menu);
		return true;
	}
	
	public void Restart(View v)
	{
		ResourcesKingdom.newPlayerChange();
		Intent intent2 = new Intent(this, StoryManager.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
        DeathEvent.this.finish();
		startActivity(intent2);
	}

}
