package au.com.patricklabes.sleepeasy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private Button activateBtn;
    private Switch ringerSwitch, flashSwitch;
    private Spinner spinnerStart, spinnerEnd;
    BatteryChecker bc;
    private AdView mAdView;
    SharedPreferenceInformationManager mI;
    private RadioButton mon_radioButton,tue_radioButton,wed_radioButton,thur_radioButton,fri_radioButton,sat_radioButton,sun_radioButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeRadioButtons();

        MobileAds.initialize(this, "ca-app-pub-1588318060537144/4535445656");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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

    private void initializeRadioButtons() {
        mon_radioButton = findViewById(R.id.radio_monday);
        tue_radioButton = findViewById(R.id.radio_tuesday);
        wed_radioButton = findViewById(R.id.radio_wednesday);
        thur_radioButton = findViewById(R.id.radio_thursday);
        fri_radioButton = findViewById(R.id.radio_friday);
        sat_radioButton = findViewById(R.id.radio_saturday);
        sun_radioButton = findViewById(R.id.radio_sunday);

        mon_radioButton.setOnClickListener(this);
        tue_radioButton.setOnClickListener(this);
        wed_radioButton.setOnClickListener(this);
        thur_radioButton.setOnClickListener(this);
        fri_radioButton.setOnClickListener(this);
        sat_radioButton.setOnClickListener(this);
        sun_radioButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        DaySelection dSRB = new DaySelection(v.getContext());
        String idValue = String.valueOf(v.getId());

         switch (v.getId()){
             case R.id.switch_flash:
                 switchButtonChecker("FLASH");
                 break;
             case R.id.switch_ringer:
                 switchButtonChecker("RINGER");
                 break;
             case R.id.btn_activate:
                 activeBtnChecker();
                 break;
             case R.id.radio_monday:
                 dSRB.radioButtonSelected("Mon");
                 mon_radioButton.setChecked(mI.getRadioButtonPosition("Mon"));
                break;
             case R.id.radio_tuesday:
                 dSRB.radioButtonSelected("Tue");
                 tue_radioButton.setChecked(mI.getRadioButtonPosition("Tue"));
                 break;
             case R.id.radio_wednesday:
                 dSRB.radioButtonSelected("Wed");
                 wed_radioButton.setChecked(mI.getRadioButtonPosition("Wed"));
                 break;
             case R.id.radio_thursday:
                 dSRB.radioButtonSelected("Thu");
                 thur_radioButton.setChecked(mI.getRadioButtonPosition("Thu"));
                 break;
             case R.id.radio_friday:
                 dSRB.radioButtonSelected("Fri");
                 fri_radioButton.setChecked(mI.getRadioButtonPosition("Fri"));
                 break;
             case R.id.radio_saturday:
                 dSRB.radioButtonSelected("Sat");
                 sat_radioButton.setChecked(mI.getRadioButtonPosition("Sat"));
                 break;
             case R.id.radio_sunday:
                 dSRB.radioButtonSelected("Sun");
                 sun_radioButton.setChecked(mI.getRadioButtonPosition("Sun"));
                 break;
         }




         if(v.getId() != R.id.btn_activate){
             canWeActivate();
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
            activateBtn.setText(R.string.deactivate);
            Toast.makeText(this,R.string.activated, Toast.LENGTH_LONG).show();
            mI.changeActivationButtonStatus(true);
        }else{
            bc.cancelAlarm(getApplicationContext());
            activateBtn.setText(R.string.activate);
            Toast.makeText(this,R.string.deactivated, Toast.LENGTH_LONG).show();
            mI.changeActivationButtonStatus(false);
        }

        mI.setNotificationStatus(false);
        mI.setPauseFalse();


    }

    public void buttonStates(){

        //Activation Button
        if (mI.activationButton()){
            activateBtn.setText(R.string.deactivate);
            //need to start activation not sure this is any use to users because they wont be install over the top like my debugs are...
            bc.setAlarm(getApplicationContext());
        }

        //radio buttons
        mon_radioButton.setChecked(mI.getRadioButtonPosition("Mon"));
        tue_radioButton.setChecked(mI.getRadioButtonPosition("Tue"));
        wed_radioButton.setChecked(mI.getRadioButtonPosition("Wed"));
        thur_radioButton.setChecked(mI.getRadioButtonPosition("Thu"));
        fri_radioButton.setChecked(mI.getRadioButtonPosition("Fri"));
        sat_radioButton.setChecked(mI.getRadioButtonPosition("Sat"));
        sun_radioButton.setChecked(mI.getRadioButtonPosition("Sun"));

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
            Toast.makeText(this,R.string.update, Toast.LENGTH_LONG).show();
            bc.setAlarm(getApplicationContext());
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
