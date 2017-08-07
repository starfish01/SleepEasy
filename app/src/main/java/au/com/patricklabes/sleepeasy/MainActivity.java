package au.com.patricklabes.sleepeasy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView, info2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)this.findViewById(R.id.infoText);
        info2 = (TextView)this.findViewById(R.id.info2);

        startBatteryService();

        BatteryChecker bc = new BatteryChecker();
        bc.setAlarm(getApplicationContext());




    }


    public void startBatteryService(){

        Intent intent = new Intent(this, BatteryChecker.class);
        startService(intent);



        //the idea is to move this over to battery service
        this.registerReceiver(this.mBatInfoReceiver,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }



    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = getApplicationContext().registerReceiver(null, ifilter);

            int status = batteryStatus.getIntExtra("level", -1);

            int pluggedIn = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,6);

            textView.setText(String.valueOf(status));
            info2.setText(String.valueOf(pluggedIn));



        }
    };











}
