package se.mah.kingdom_v02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
    	 
        Intent intent2 = new Intent(context, DecideCall.class);
        context.startService(intent2);
    } 
}
