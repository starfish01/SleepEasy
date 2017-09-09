package au.com.patricklabes.sleepeasy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by patrick on 8/09/2017.
 */

public class ScreenColorChanger extends AppCompatActivity implements View.OnClickListener{

    Button deactivationButton;
    LinearLayout backgroundScreen;
    boolean end;
    ObjectAnimator oA;


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

        FlashScreen();


    }

    public void  FlashScreen(){
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
    public void onClick(View v) {
        backgroundScreen.setBackgroundColor(Color.WHITE);
        // sending back the data that were done here this might be helpful
        //https://stackoverflow.com/questions/920306/sending-data-back-to-the-main-activity-in-android

        end = true;
    }
}
