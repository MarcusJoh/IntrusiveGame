package se.mah.kingdom;

import java.util.Calendar;
import java.util.Random;
import java.util.Timer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class DecideCall extends Service{

	private int update = 1;
	private int timesHappend = 0;
	private double increaseInterval=60*4;
	private int increaseRate = 1;
	private double totalTimeElapsed = 0;
	
	
	  @Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
	    //TODO do something useful
		  SetAlarm();
	    return Service.START_NOT_STICKY;
	  }

	public void SetAlarm()
	{
		totalTimeElapsed += update;
		Calendar c = Calendar.getInstance();
		Random rand = new Random();
		Log.i("Resources", String.valueOf(ResourcesKingdom.CombinedResources()));
		if (!ResourcesKingdom.isSomthingBad()) {
				StartCall();
		}
		else
			return;
			
		int min = ((60 - c.get(Calendar.MINUTE)) / increaseRate);
		int max = 60 / increaseRate;
		int random = rand.nextInt(max);
		update = random + min;
		int maxMins = (max + min);
		UpdateRate_Increase();
		AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(this,AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		Calendar time = Calendar.getInstance();
		time.setTimeInMillis(System.currentTimeMillis());
		time.add(Calendar.MINUTE, update);
		Log.i("Update", String.valueOf(update));
		alarmMgr.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);
		
	}

	public void StartCall()
	{
        Intent intent2 = new Intent(this, Event.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
        this.startActivity(intent2);
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

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
