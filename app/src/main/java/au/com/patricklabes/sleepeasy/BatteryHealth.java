package au.com.patricklabes.sleepeasy;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by patrick on 07-Aug-17.
 */

public class BatteryHealth extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Log.d("inside battery health","0");

        this.registerReceiver(this.mBatInfoReceiver,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        //getChargeStatus();

    }

    int chargeStatus, chargePercent;

    public void getChargeStatus(){
        //IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        //Intent batteryStatus = getApplicationContext().registerReceiver(null, ifilter);

        //int status = batteryStatus.getIntExtra("level", -1);

        //return status;

        //finish();
    }


    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = getApplicationContext().registerReceiver(null, ifilter);

            int status = batteryStatus.getIntExtra("level", -1);

            int pluggedIn = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,6);

            //textView.setText(String.valueOf(status));
            //info2.setText(String.valueOf(pluggedIn));



        }
    };



}
