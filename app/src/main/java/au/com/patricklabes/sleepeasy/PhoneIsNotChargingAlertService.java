package au.com.patricklabes.sleepeasy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by patrick on 8/09/2017.
 */

public class PhoneIsNotChargingAlertService extends Service {

    SharedPreferenceInformationManager mI;
    Boolean flasher, ringer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        ringer = mI.getRingerSwitch();
        flasher = mI.getFlashSwitch();

        Log.d("what is the position",String.valueOf(flasher));

        if(flasher){
            Intent dialogIntent = new Intent(this, ScreenColorChanger.class);
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(dialogIntent);
        }


        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mI = new SharedPreferenceInformationManager(this);

    }
}
