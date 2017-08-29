package au.com.patricklabes.sleepeasy;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationHandler {



    public NotificationCompat.Builder notificationWarning(Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

                builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Hello world")
                .setContentTitle("Yo Yo");

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,0, notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);



        return builder;
    }




}
