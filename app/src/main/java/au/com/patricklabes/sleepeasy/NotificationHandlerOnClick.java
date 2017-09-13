package au.com.patricklabes.sleepeasy;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;


public class NotificationHandlerOnClick extends Service {

    public void onCreate(){
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(852);

        SharedPreferenceInformationManager sp = new SharedPreferenceInformationManager(this);
        sp.setPauseTrue();

    }


    public NotificationHandlerOnClick() {


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }





}
