package au.com.patricklabes.sleepeasy;


import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class NotificationHandler {






    public NotificationCompat.Builder notificationWarning(Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        //Intent notificationIntent = new Intent(context, MainActivity.class);

        Intent notificationIntent = new Intent(context,NotificationHandlerOnClick.class);

        PendingIntent contentIntent = PendingIntent.getService(context,0, notificationIntent,PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(contentIntent);

        NotificationCompat.Action action = new NotificationCompat.Action(
                R.drawable.ic_alarm_off,
                "Pause Alert",contentIntent);


                builder.setSmallIcon(R.drawable.ic_battery_unknown)
                .setContentTitle("Sleep Easy")
                .setContentText("The Device has not been connected to a charger? Do you want to pause the alert?")
                .addAction(action);



        builder.setAutoCancel(true);



        return builder;
    }







}
