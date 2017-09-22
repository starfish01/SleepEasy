package au.com.patricklabes.sleepeasy;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationHandler {


    public NotificationCompat.Builder notificationWarning(Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

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

    public void cancelTheNotification(Context context){


        NotificationManager mN = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        mN.cancel(852);
    }







}
