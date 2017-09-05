package au.com.patricklabes.sleepeasy;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by patri on 03-Sep-17.
 */

public class PhoneIsNotChargingAlert extends AppCompatActivity{

    SharedPreferenceInformationManager mI;
    Boolean flasher, ringer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_screen_layout);

        Log.d("Did we get here","2");

        //need to test that this works
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
            + WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
            + WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
            + WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        mI = new SharedPreferenceInformationManager(this);
        ringer = mI.getRingerSwitch();
        flasher = mI.getFlashSwitch();

        //launch

        FlashScreen();

    }


    public void FlashScreen(){

        Log.d("Color choice","Black");
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);

    }




}
