package au.com.patricklabes.sleepeasy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by patrick on 4/08/2017.
 */

public class BatteryService extends Service {

    BatteryChecker batteryChecker = new BatteryChecker();

    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        batteryChecker.setAlarm(this);
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId){
        batteryChecker.setAlarm(this);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}








