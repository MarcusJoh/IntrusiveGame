package se.mah.kingdom_v02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
    	 
        Intent intent2 = new Intent(context, DecideCall.class);
        //intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
        context.startService(intent2);
    } 
    

}
