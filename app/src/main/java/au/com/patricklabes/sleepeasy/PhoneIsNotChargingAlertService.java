package au.com.patricklabes.sleepeasy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by patrick on 8/09/2017.
 */

public class PhoneIsNotChargingAlertService extends Service {

    SharedPreferenceInformationManager mI;
    Boolean ringer, deactivated;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        ringer = mI.getRingerSwitch();

            Intent dialogIntent = new Intent(this, PhoneIsNotChargingAlert.class);
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(dialogIntent);



        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mI = new SharedPreferenceInformationManager(this);

    }




}
