package au.com.patricklabes.sleepeasy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView, info2;
    private Button activateBtn;
    private Switch alarmSwitch, flashSwitch;
    private int batteryPercent, pluggedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = this.getSharedPreferences("au.com.shifttech", 0);





        activateBtn = (Button)this.findViewById(R.id.btn_activate);
        activateBtn.setOnClickListener(this);

        alarmSwitch = (Switch)this.findViewById(R.id.switch_ringer);
        flashSwitch = (Switch)this.findViewById(R.id.switch_flash);

        alarmSwitch.setOnClickListener(this);
        flashSwitch.setOnClickListener(this);


        BatteryChecker bc = new BatteryChecker();
        bc.setAlarm(getApplicationContext());

    }


    @Override
    public void onClick(View v) {

         switch (v.getId()){
             case R.id.switch_flash:

                 break;
             case R.id.switch_ringer:

                 break;
             case R.id.btn_activate:

                 break;

         }

    }

    public void saveStates(){


    }


}
