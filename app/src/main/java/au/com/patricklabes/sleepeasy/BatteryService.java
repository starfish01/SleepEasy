package au.com.patricklabes.sleepeasy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by patri on 30-Jul-17.
 */

public class BatteryService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {

        Toast.makeText(this,"started Service...", Toast.LENGTH_LONG).show();
        notifyMe();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    public void notifyMe(){

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);
        mBuilder
                .setSmallIcon(R.mipmap.ic_warning)
                .setContentTitle("Battery Warning")
                .setContentText("It appears you might not have your device plugged in");


        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);



        int mBuilderID = 001;

        NotificationManager mNotify =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotify.notify(mBuilderID,mBuilder.build());



    }






}
