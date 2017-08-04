package au.com.patricklabes.sleepeasy;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by patrick on 4/08/2017.
 */

public class BatteryService extends IntentService {

    public BatteryService(){
        super("My_Worker_Thread");
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId){

        return super.onStartCommand(intent,flags,startId);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        synchronized (this)
        {
            int count =0;

        }



    }
}
