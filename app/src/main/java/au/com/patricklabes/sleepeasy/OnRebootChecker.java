package au.com.patricklabes.sleepeasy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


public class OnRebootChecker extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startService = new Intent(context,OnRebootChecker.class);
        context.startService(startService);

        SharedPreferences prefs = context.getSharedPreferences("au.com.shifttech", 0);
        boolean activatedStatus = prefs.getBoolean("ACTIVATED",false);

        if (activatedStatus){
            BatteryChecker bc = new BatteryChecker();
            bc.setAlarm(context);
        }




    }
}
