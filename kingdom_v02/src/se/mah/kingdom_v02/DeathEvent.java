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
	
	protected void onCreate(Bundle savedInstanceState,Intent intent) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_death_event);
		Bundle extras = intent.getExtras();
		String empty = extras.getString("resource");
		deathSentence = (TextView) findViewById(id.grave_headline);
		if(empty == "food")
			deathSentence.setText("You starved to death");
		else if(empty == "gold")
			deathSentence.setText("You went bankruppt");
		else
			deathSentence.setText("You died during the revolt");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.death_event, menu);
		return true;
	}
	
	public void Restart(View v)
	{
		Calendar c = Calendar.getInstance();
		Random rand = new Random();
		int update = 0;
		Log.i("Resources", String.valueOf(ResourcesKingdom.CombinedResources()));
		int min = ((60 - c.get(Calendar.MINUTE)) );
		int max = 60;
		int random = rand.nextInt(max);
		update = random + min;
		int maxMins = (max + min);
		AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(this,AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		Calendar time = Calendar.getInstance();
		time.setTimeInMillis(System.currentTimeMillis());
		time.add(Calendar.SECOND, update);
		Log.i("Update", String.valueOf(update));
		alarmMgr.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);
		Intent intent2 = new Intent(this, StoryManager.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		this.startActivity(intent2);
	}

}
