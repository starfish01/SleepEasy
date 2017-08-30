package au.com.patricklabes.sleepeasy;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;


public class NotificationHandlerOnClick extends Service {


    public NotificationHandlerOnClick() {

        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(852);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
