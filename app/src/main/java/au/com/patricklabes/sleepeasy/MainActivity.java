package au.com.patricklabes.sleepeasy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent serviceIntent = new Intent(this, BatteryService.class);
        startService(serviceIntent);

        int isCharging = BatteryManager.BATTERY_STATUS_CHARGING;

        TextView textView = (TextView) findViewById(R.id.infoText);
        textView.setText(Integer.toString(isCharging));

    }








}
