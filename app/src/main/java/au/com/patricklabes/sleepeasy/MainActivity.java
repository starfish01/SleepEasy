package au.com.patricklabes.sleepeasy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView, info2;
    private Button activateBtn;
    private Switch alarmSwitch, flashSwitch;
    private int batteryPercent, pluggedIn;
    private EditText inputStartTime, inputEndTime;


    SharedPrefrenceInformationManager mI = new SharedPrefrenceInformationManager(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inputStartTime = (EditText)this.findViewById(R.id.input_startTime);
        inputEndTime = (EditText)this.findViewById(R.id.input_endTime);

        activateBtn = (Button)this.findViewById(R.id.btn_activate);
        activateBtn.setOnClickListener(this);

        alarmSwitch = (Switch)this.findViewById(R.id.switch_ringer);
        flashSwitch = (Switch)this.findViewById(R.id.switch_flash);

        alarmSwitch.setOnClickListener(this);
        flashSwitch.setOnClickListener(this);

        //temp while i iron the rest out
        //inputStartTime.setFocusable(false);
        //inputEndTime.setFocusable(false);



        //buttonStates();


    }




    @Override
    public void onClick(View v) {

         switch (v.getId()){
             case R.id.switch_flash:


                 break;
             case R.id.switch_ringer:

                 break;
             case R.id.btn_activate:
                 activeBtnChecker();
                 break;

         }

    }

    public void saveStates(){



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

    }






}
