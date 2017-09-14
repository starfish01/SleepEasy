package au.com.patricklabes.sleepeasy;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by patrick on 4/08/2017.
 */

public class BatteryChecker extends BroadcastReceiver {

    SharedPreferenceInformationManager mI;
    //Context context;




    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("Do we get here","2");

        //context = this.context;

        Log.d("Do we get here","3");
        PowerManager pm =(PowerManager) context.getSystemService(context.POWER_SERVICE);
        Log.d("Do we get here","3.1");
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"");
        Log.d("Do we get here","3.2");
        wl.acquire();

        Log.d("Do we get here","4");

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.getApplicationContext().registerReceiver(null, ifilter);

        Log.d("Do we get here","5");

        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;
        boolean fullyCharged = status == BatteryManager.BATTERY_STATUS_FULL;
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);

        SharedPreferences prefs = context.getSharedPreferences("au.com.shifttech", 0);

        Log.d("Do we get here","6");

        if(isCharging){
            Log.d("BatteryChecker","We are charging");
            Log.d("Do we get here","6.1");
            wl.release();
            return;
        }

        Log.d("Do we get here","7");

        // something is wrong with the notification status
        //TODO look into notification status and how it works


        //check time
        if (checkIfItsTime(context)) {
            if (!mI.getNotificationStatus()) {
                Log.d("Do we get here","7.1");
                //fire notification
                mI.setNotificationStatus(false);
                fireNotification(context);
                // return
                wl.release();
                return;
            }else if(!mI.positionPause()) {
                //fire alert not connected

                Log.d("Do we get here","7.2");
                Intent intent1 = new Intent(context,PhoneIsNotChargingAlert.class);
                context.startActivity(intent1);


            }
        } else {
            Log.d("Do we get here","8.1");
            mI.setPauseFalse();
            Log.d("Do we get here","8.2");
            mI.setNotificationStatus(true);
            Log.d("Do we get here","8.3");
            wl.release();
            Log.d("Do we get here","8.4");
            return;
        }

        Log.d("Do we get here","9");




        //

        int stats = prefs.getInt("BATTERY_PERCENT",0);


        Toast.makeText(context,String.valueOf(level), Toast.LENGTH_LONG).show();

        wl.release();


    }

    public void setAlarm(Context context){

        //AlarmManager.setExact use to set to start recieving after certain time and then to cancel at
        //specified time
        // https://developer.android.com/reference/android/app/AlarmManager.html#setExact(int, long, android.app.PendingIntent)

        Log.d("Do we get here","1");

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, BatteryChecker.class);
        PendingIntent pi = PendingIntent.getBroadcast(context,0,i,0);

        // testing the strain on the battery over the day

        //need to change this for testing***********************************************************************************************

        //am.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),pi);
        am.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),60000,pi);
        //something here must be wrong
        //am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60000, pi);
        //am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_HALF_HOUR, pi);







    }

    public void cancelAlarm(Context context){
        Intent intent = new Intent(context, BatteryChecker.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }


    private boolean checkIfItsTime(Context context){
        Log.d("Do we get here","7.time1");
        mI = new SharedPreferenceInformationManager(context);
        Log.d("Do we get here","7.time2");

        Calendar cal = Calendar.getInstance();
        int currentHour = cal.get(Calendar.HOUR_OF_DAY);
        int startTime = mI.getStartTime();
        int endTime = mI.getEndTime();

        Log.d("Do we get here","7.time3");
        if (endTime != 0){
            endTime -= 1;
        }else{
            endTime = 23;
        }

        //int ticker[];
        List<Integer> ticker = new ArrayList<>();

        ticker.add(startTime);

        while (startTime != endTime){
            if (startTime == 23){
                startTime = 0;
            }else {
                startTime += 1;
            }
            ticker.add(startTime);
        }

        return ticker.contains(currentHour);
    }

    private void fireNotification(Context context){
        NotificationHandler notification = new NotificationHandler();
        NotificationCompat.Builder builder = notification.notificationWarning(context);
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        SharedPreferenceInformationManager sp = new SharedPreferenceInformationManager(context);
        manager.notify(852,builder.build());
    }
















}
