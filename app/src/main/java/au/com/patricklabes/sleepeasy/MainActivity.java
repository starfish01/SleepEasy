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

/*
        this.registerReceiver(this.mBatInfoReceiver,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
*/
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = getApplicationContext().registerReceiver(null, ifilter);

        /*
        https://stackoverflow.com/questions/3291655/get-battery-level-and-state-in-android
         */

        BatteryManager bm = (BatteryManager)getSystemService(BATTERY_SERVICE);
        int batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        int status = batteryStatus.getIntExtra("level", -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        textView.setText(String.valueOf(status));



    }




    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level",0);
            textView.setText(String.valueOf(level)+"%");

            String action = intent.getAction();

            info2.setText(action);

            if(action.equals(Intent.ACTION_POWER_CONNECTED)){
                info2.setText("Connected");
            }else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)){
                info2.setText("Disconnected");
            }


        }
    };

    private BroadcastReceiver chargeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           info2.setText("power connected");
        }
    };

    private BroadcastReceiver chargeReceiveroff = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            info2.setText("power disconnected");
        }
    };











}
