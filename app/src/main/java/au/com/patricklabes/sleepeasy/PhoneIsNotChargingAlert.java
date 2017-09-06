package au.com.patricklabes.sleepeasy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import static android.animation.ObjectAnimator.ofObject;

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

        colorOne();

    }

    private void colorOne(){
        backgroundScreen = (LinearLayout)findViewById(R.id.alertScreenLayout);


        //int colorId = ((ColorDrawable) backgroundScreen.getBackground()).getColor();
        ObjectAnimator oA = ObjectAnimator.ofObject(backgroundScreen,"backgroundColor",new ArgbEvaluator(),Color.YELLOW,Color.BLACK);
        oA.setDuration(10000);
        oA.start();


        oA.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                colorTwo();
            }
        });

    }

    private void colorTwo(){
        backgroundScreen = (LinearLayout)findViewById(R.id.alertScreenLayout);


        int colorId = ((ColorDrawable) backgroundScreen.getBackground()).getColor();
        ObjectAnimator oA = ObjectAnimator.ofObject(backgroundScreen,"backgroundColor",new ArgbEvaluator(),colorId,Color.YELLOW);
        oA.setDuration(10000);
        oA.start();


        oA.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                colorOne();
            }
        });

    }






}
