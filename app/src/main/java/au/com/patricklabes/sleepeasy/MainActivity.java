package au.com.patricklabes.sleepeasy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent serviceIntent = new Intent(this, BatteryService.class);
        startService(serviceIntent);

        //notifyMe();

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
