package au.com.patricklabes.sleepeasy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private Button activateBtn;
    private Switch ringerSwitch, flashSwitch;
    private Spinner spinnerStart, spinnerEnd;
    BatteryChecker bc;



    SharedPreferenceInformationManager mI;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mI = new SharedPreferenceInformationManager(this);
        bc = new BatteryChecker();

        //spinners
        spinnerStart = (Spinner)findViewById(R.id.spinnerStart);
        spinnerEnd = (Spinner)findViewById(R.id.spinnerEnd);

        spinnerStart.setOnItemSelectedListener(this);
        spinnerEnd.setOnItemSelectedListener(this);


        //
        activateBtn = (Button)this.findViewById(R.id.btn_activate);
        activateBtn.setOnClickListener(this);

        ringerSwitch = (Switch)this.findViewById(R.id.switch_ringer);
        flashSwitch = (Switch)this.findViewById(R.id.switch_flash);
        ringerSwitch.setOnClickListener(this);
        flashSwitch.setOnClickListener(this);

        canWeActivate();

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
                 activeBtnChecker();
                 break;

         }

    }

    private void canWeActivate(){
        if (!mI.getRingerSwitch() && !mI.getFlashSwitch()){
            if(mI.activationButton()){
                activeBtnChecker();
            }
            activateBtn.setEnabled(false);
        }else{
            if(mI.activationButton()){
                bc.setAlarm(getApplicationContext());
                Toast.makeText(this,"Updated...", Toast.LENGTH_LONG).show();
            }

            activateBtn.setEnabled(true);
        }
    }






    private void switchButtonChecker(String switchThatWasFlipped){
        mI.setSwitch(switchThatWasFlipped);
    }



    private void activeBtnChecker(){

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

        mI.setNotificationStatus(false);
        mI.setPauseFalse();


    }

    public void buttonStates(){

        //Activation Button
        if (mI.activationButton()){
            activateBtn.setText("Deactive");
            //need to start activation not sure this is any use to users because they wont be install over the top like my debugs are...
            bc.setAlarm(getApplicationContext());
        }


        //Setting switch States
        ringerSwitch.setChecked(mI.getRingerSwitch());
        flashSwitch.setChecked(mI.getFlashSwitch());

        //spinner set times
        spinnerStart.setSelection(mI.getStartTime());
        spinnerEnd.setSelection(mI.getEndTime());

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;

        switch (spinner.getId()){
            case R.id.spinnerStart:
                mI.setStartTime(position);
                break;
            case R.id.spinnerEnd:
                mI.setEndTime(position);
                break;
        }

        if(mI.activationButton()){
            Toast.makeText(this,"Updated...", Toast.LENGTH_LONG).show();
            bc.setAlarm(getApplicationContext());
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
