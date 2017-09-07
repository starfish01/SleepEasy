package au.com.patricklabes.sleepeasy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by patri on 03-Sep-17.
 */

public class PhoneIsNotChargingAlert extends AppCompatActivity implements View.OnClickListener{

    SharedPreferenceInformationManager mI;
    Boolean flasher, ringer;
    Handler handler;
    LinearLayout backgroundScreen;
    Button deactivationButton;
    ObjectAnimator oA;
    boolean end;

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

        deactivationButton = (Button) this.findViewById(R.id.alertDeavtivateButton);
        deactivationButton.setOnClickListener(this);

        mI = new SharedPreferenceInformationManager(this);
        ringer = mI.getRingerSwitch();
        flasher = mI.getFlashSwitch();

        backgroundScreen = (LinearLayout)findViewById(R.id.alertScreenLayout);

        if(flasher){
            FlashScreen();
        }


    }


    public void FlashScreen(){
        backgroundScreen.setBackgroundColor(Color.YELLOW);
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
    public void onClick(View v) {
        backgroundScreen.setBackgroundColor(Color.WHITE);
        end = true;
    }
}
