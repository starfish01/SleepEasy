package au.com.patricklabes.sleepeasy;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.content.Context.POWER_SERVICE;


public class BatteryChecker extends BroadcastReceiver {

    SharedPreferenceInformationManager mI;


    @Override
    public void onReceive(Context context, Intent intent) {

        PowerManager pm =(PowerManager) context.getSystemService(POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"");
        wl.acquire();

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.getApplicationContext().registerReceiver(null, ifilter);

        mI = new SharedPreferenceInformationManager(context);

        int status = 0;
        try {
            status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;


        if(isCharging){
            mI.setPauseFalse();
            mI.setNotificationStatus(false);
            wl.release();
            return;
        }


        //check time
        if (checkIfItsTime(context)) {
            if (!mI.getNotificationStatus()) {
                //fire notification
                mI.setNotificationStatus(true);
                fireNotification(context);
                // return
                wl.release();
                return;
            }else if(!mI.positionPause()) {
                //fire alert not connected

                Intent intent1 = new Intent(context,PhoneIsNotChargingAlert.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent1);


            }
        } else {
            mI.setPauseFalse();
            mI.setNotificationStatus(false);
            wl.release();
            return;
        }







        wl.release();


    }

    public void setAlarm(Context context){

        //AlarmManager.setExact use to set to start recieving after certain time and then to cancel at
        //specified time
        // https://developer.android.com/reference/android/app/AlarmManager.html#setExact(int, long, android.app.PendingIntent)

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, BatteryChecker.class);
        PendingIntent pi = PendingIntent.getBroadcast(context,0,i,0);

        //every minute for testing
        //am.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),60000,pi);

        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_HALF_HOUR, pi);







    }

    public void cancelAlarm(Context context){
        Intent intent = new Intent(context, BatteryChecker.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }


    private boolean checkIfItsTime(Context context){
        mI = new SharedPreferenceInformationManager(context);

        Calendar cal = Calendar.getInstance();
        int currentHour = cal.get(Calendar.HOUR_OF_DAY);
        int startTime = mI.getStartTime();
        int endTime = mI.getEndTime();

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
        NotificationManager manager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);

        //setting up notification channel
        String id = "sleep_easy_channel_01";
        CharSequence name = "Sleep Easy";
        String description = "Do we need to charge?";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        //continue here
        //https://developer.android.com/guide/topics/ui/notifiers/notifications.html



        manager.notify(852,builder.build());

        //this is for sound but not sure if i will need after notification channel is set up
        Notification notify = new Notification();
        notify.defaults = Notification.DEFAULT_ALL;



    }


}
