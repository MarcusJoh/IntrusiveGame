package se.mah.kingdom_v02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
    	 
        Toast.makeText(context, "Alarm went off", Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent(context, DecideCall.class);
        //intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
        context.startService(intent2);
    } 
    

}
