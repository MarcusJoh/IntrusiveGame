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

	private Timer timer =  new Timer();
	private int update = 1;
	private int timesHappend = 0;
	private Calendar time = Calendar.getInstance();
	private double increaseInterval= 1 * 60;;
	private int increaseRate = 1;
	private double totalTimeElapsed = 0;
	private int likenessOfEmergency = 1;
	private boolean emergencyHappening = false;
	
	
	
//	protected void onCreate(Bundle savedInstanceState) {
//		timer = new Timer();
//		time = Calendar.getInstance();
//		update = 1;
//		increaseInterval = 1 * 60;
//		timesHappend = 0;
//		increaseRate = 1;
//		totalTimeElapsed = 0;
//		likenessOfEmergency = 1;
//		emergencyHappening = false;
////		SetAlarm();
//
//	};
	  @Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
	    //TODO do something useful
		  SetAlarm();
//	        Intent intent2 = new Intent(this, Event.class);
//	        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
//	        this.startActivity(intent2);
	    return Service.START_NOT_STICKY;
	  }

	public void SetAlarm()
	{
		totalTimeElapsed += update / 60000;
		emergencyHappening = false;
		Calendar c = Calendar.getInstance();
		Random rand = new Random();
		Log.i("Resources", String.valueOf(ResourcesKingdom.CombinedResources()));
		int numb = 0;
		if(numb==0)
			StartCall();
//		if (!Resources.isSomthingBad()) {
//			int emergency = rand.nextInt(Resources.CombinedResources());
//			if (likenessOfEmergency == emergency) {
//				StartCall(this);
//			}
//		}
//		else
//		{
//			StartCall(this);
//		}
		int min = ((60 - c.get(Calendar.MINUTE)) / increaseRate) * 60000;
		int max = 3600000 / increaseRate;
		int random = rand.nextInt(max);
		update = random + min;
		int maxMins = (max + min) / 60000;
//		UpdateRate_Increase();
//		Debug(maxMins, random / 60000, min / 60000, max / 60000);
		timesHappend += 1;
		AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(this,AlarmReceiver.class);
		intent.putExtra("emergency", true);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		Calendar time = Calendar.getInstance();
		time.setTimeInMillis(System.currentTimeMillis());
		time.add(Calendar.SECOND, 20);
		Log.i("Update", String.valueOf(update));
		alarmMgr.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);
		
	}

	public void StartCall()
	{
        Intent intent2 = new Intent(this, Event.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
        this.startActivity(intent2);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
