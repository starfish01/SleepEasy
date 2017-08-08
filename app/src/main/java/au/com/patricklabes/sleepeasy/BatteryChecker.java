package au.com.patricklabes.sleepeasy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by patrick on 4/08/2017.
 */

public class BatteryChecker extends BroadcastReceiver {



    /*
    https://stackoverflow.com/questions/4459058/alarm-manager-example

    need to look into; AlarmManager,0000 PowerManager

     */

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm =(PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"");
        wl.acquire();

        //code goes here


        Log.d("the enter","0");

        //Intent intent48 = new Intent(context, BatteryHealth.class);

        BatteryHealth bh = new BatteryHealth();
        int charge = bh.getChargeStatus(context);

        Toast.makeText(context,"hello", Toast.LENGTH_LONG).show();

        wl.release();
    }

    public void setAlarm(Context context){
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, BatteryChecker.class);
        PendingIntent pi = PendingIntent.getBroadcast(context,0,i,0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60000, pi);



    }

    public void cancelAlarm(Context context){
        Intent intent = new Intent(context, BatteryChecker.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }


}
