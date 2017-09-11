package au.com.patricklabes.sleepeasy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView, info2;
    private Button activateBtn;
    private Switch ringerSwitch, flashSwitch;
    private int batteryPercent, pluggedIn;
    private EditText inputStartTime, inputEndTime;


    SharedPreferenceInformationManager mI;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mI = new SharedPreferenceInformationManager(this);


        //switch to spinner one day
        inputStartTime = (EditText)this.findViewById(R.id.input_startTime);
        inputEndTime = (EditText)this.findViewById(R.id.input_endTime);

        activateBtn = (Button)this.findViewById(R.id.btn_activate);
        activateBtn.setOnClickListener(this);

        ringerSwitch = (Switch)this.findViewById(R.id.switch_ringer);
        flashSwitch = (Switch)this.findViewById(R.id.switch_flash);
        ringerSwitch.setOnClickListener(this);
        flashSwitch.setOnClickListener(this);

        canWeActivate();



        //temp while i iron the rest out (probably switch to a spinner)
        inputStartTime.setFocusable(false);
        inputEndTime.setFocusable(false);



        buttonStates();


    }




    @Override
    public void onClick(View v) {

         switch (v.getId()){
             case R.id.switch_flash:
                 switchButtonChecker("FLASH");
                 canWeActivate();

                 break;
             case R.id.switch_ringer:
                 switchButtonChecker("RINGER");
                 canWeActivate();

                 break;
             case R.id.btn_activate:
                 //activeBtnChecker();

                 testExample();
                 break;

         }

    }

    private void canWeActivate(){
        if (!mI.getRingerSwitch() && !mI.getFlashSwitch()){
            activateBtn.setEnabled(false);
        }else{
            activateBtn.setEnabled(true);
        }

    }



    private void testExample(){
        Log.d("Did we get here","1");




        //Switching to a service to handle interactions
        Intent intent1 = new Intent(getApplicationContext(),PhoneIsNotChargingAlert.class);
        startActivity(intent1);


        /*
        NotificationHandler notification = new NotificationHandler();
        NotificationCompat.Builder builder = notification.notificationWarning(this);
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        SharedPreferenceInformationManager sp = new SharedPreferenceInformationManager(getApplicationContext());
        manager.notify(852,builder.build());
         */

    }


    private void switchButtonChecker(String switchThatWasFlipped){
        mI.setSwitch(switchThatWasFlipped);
    }



    private void activeBtnChecker(){
        BatteryChecker bc = new BatteryChecker();

        if (!mI.activationButton()){
            bc.setAlarm(getApplicationContext());
            activateBtn.setText("Deactivate");
            Toast.makeText(this,"Active", Toast.LENGTH_LONG).show();
            mI.changeActivationButtonStatus(true);
        }else{
            bc.cancelAlarm(getApplicationContext());
            activateBtn.setText("Activate");
            Toast.makeText(this,"Deactivated", Toast.LENGTH_LONG).show();
            mI.changeActivationButtonStatus(false);
        }
    }

    public void buttonStates(){

        //Activation Button
        if (mI.activationButton()){
            activateBtn.setText("Deactive");
        }
        //Seting Times
        inputStartTime.setText(String.valueOf(mI.getStartTime()));
        inputEndTime.setText(String.valueOf(mI.getEndTime()));

        //Setting switch States
        ringerSwitch.setChecked(mI.getRingerSwitch());
        flashSwitch.setChecked(mI.getFlashSwitch());


    }






}
