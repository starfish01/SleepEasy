package au.com.patricklabes.sleepeasy;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by patri on 31-Jul-17.
 */

public class Notification extends NotificationCompat.Builder {


    /**
     * @param context
     * @inheritDoc
     */
    public Notification(Context context) {
        super(context);
    }

    public void setNotification(View view){


    }




}
