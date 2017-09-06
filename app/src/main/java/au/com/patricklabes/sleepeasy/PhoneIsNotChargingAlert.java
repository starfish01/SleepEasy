package au.com.patricklabes.sleepeasy;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by patri on 03-Sep-17.
 */

public class PhoneIsNotChargingAlert extends AppCompatActivity{

    SharedPreferenceInformationManager mI;
    Boolean flasher, ringer;
    Handler handler;
    LinearLayout backgroundScreen;

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

        //ScreenColorChanger sc = new ScreenColorChanger();

        //ScreenColorChanger.colorLight();

        FlashScreen();

    }


    public void FlashScreen(){
        backgroundScreen = (LinearLayout)findViewById(R.id.alertScreenLayout);

        backgroundScreen.setBackgroundColor(Color.BLACK);


        //Stuck Here ...
        //ObjectAnimator colorFade = ObjectAnimator.ofObject(backgroundScreen,new ArgbEvaluator(),,Color.WHITE);


        Log.d("Color choice","Black");

    }




}
