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
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    public BatteryHealth() {
        super();
    }

    int status;




    public int getChargeStatus(Context context){

        Log.d("0","0");

        startBatteryService();

        //BatteryManager bm = (BatteryManager)getSystemService(BATTERY_SERVICE);
        Log.d("1","1");

        //int status = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        Log.d("2","2");

        int status = 5;

        return status;
    }

    public void startBatteryService(){

        //Intent intent = new Intent(this, BatteryChecker.class);
        //startService(intent);



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

            //textView.setText(String.valueOf(status));
            //info2.setText(String.valueOf(pluggedIn));



        }
    };





}
