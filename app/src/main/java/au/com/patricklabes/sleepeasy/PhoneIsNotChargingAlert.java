package au.com.patricklabes.sleepeasy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by patrick on 8/09/2017.
 */

public class PhoneIsNotChargingAlert extends AppCompatActivity implements View.OnClickListener{

    Button deactivationButton;
    LinearLayout backgroundScreen;
    boolean flasher,ringer, end;
    ObjectAnimator oA;
    SharedPreferenceInformationManager mI;
    MediaPlayer mp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alert_screen_layout);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                + WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                + WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                + WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        deactivationButton = (Button) this.findViewById(R.id.alertDeavtivateButton);
        deactivationButton.setOnClickListener(this);
        backgroundScreen = (LinearLayout)findViewById(R.id.alertScreenLayout);

        mI = new SharedPreferenceInformationManager(this);


        flasher = mI.getFlashSwitch();
        ringer = mI.getRingerSwitch();

       // Log.d("Did we get here",String.valueOf(mI.getFlashSwitch()));

        if(mI.getFlashSwitch()){
            flashScreen();
        }

        if (mI.getRingerSwitch()){
           ringerAlert();
        }

        //flashScreen();



    }

    private void ringerAlert() {
        mp = MediaPlayer.create(this,R.raw.siren);
        mp.setLooping(true);
        mp.start();
    }

    public void  flashScreen(){
        backgroundScreen.setBackgroundColor(Color.YELLOW);
        backgroundScreen.invalidate();
        colorOne();
    }



    private void colorOne(){
        if (end){
            backgroundScreen.setBackgroundColor(Color.WHITE);
            return;
        }

        int colorFrom = ((ColorDrawable) backgroundScreen.getBackground()).getColor();
        int colorTo = Color.BLACK;

        if (colorFrom == Color.BLACK){
            colorTo = Color.BLACK;
            colorFrom = Color.YELLOW;
        }

        oA = ObjectAnimator.ofObject(backgroundScreen,"backgroundColor",new ArgbEvaluator(),colorFrom,colorTo);
        oA.setDuration(1000);
        oA.start();



        oA.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                colorOne();
            }
        });

    }

    @Override
    public void onBackPressed() {
        onExit();

    }

    @Override
    public void onClick(View v) {
        onExit();
        }

    public void onExit(){
        backgroundScreen.setBackgroundColor(Color.WHITE);
        if(mI.getRingerSwitch()){
            mp.stop();
        }
        super.finish();

    }


}
