package au.com.patricklabes.sleepeasy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
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


        Log.d("the enter","0");

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.getApplicationContext().registerReceiver(null, ifilter);
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;
        boolean fullyCharged = status == BatteryManager.BATTERY_STATUS_FULL;
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);


        SharedPreferences prefs = context.getSharedPreferences("au.com.shifttech", 0);

        int stats = prefs.getInt("BATTERY_PERCENT",0);

        Log.d("StatusCaller",String.valueOf(stats));


        Toast.makeText(context,String.valueOf(level), Toast.LENGTH_LONG).show();

        wl.release();


    }

    public void setAlarm(Context context){

        //AlarmManager.setExact use to set to start recieving after certain time and then to cancel at
        //specified time
        // https://developer.android.com/reference/android/app/AlarmManager.html#setExact(int, long, android.app.PendingIntent)


        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, BatteryChecker.class);
        PendingIntent pi = PendingIntent.getBroadcast(context,0,i,0);

        // testing the strain on the battery over the day
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_HALF_HOUR, pi);





    }

    public void cancelAlarm(Context context){
        Intent intent = new Intent(context, BatteryChecker.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
















}
