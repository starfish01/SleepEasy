package au.com.patricklabes.sleepeasy;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by patri on 07-Aug-17.
 */

public class BatteryHealth extends Activity{

    int chargeStatus, chargePercent;

    public int getChargeStatus(){
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = getApplicationContext().registerReceiver(null, ifilter);

        int status = batteryStatus.getIntExtra("level", -1);

        return status;
    }


}
