package au.com.patricklabes.sleepeasy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView, info2;
    private Button activateBtn;
    private int batteryPercent, pluggedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = this.getSharedPreferences("au.com.shifttech", 0);




        //activateBtn = (Button)this.findViewById(R.id.activateBtn);
        //activateBtn.setOnClickListener(this);
        //textView = (TextView)this.findViewById(R.id.infoText);
        //info2 = (TextView)this.findViewById(R.id.info2);


       // startBatteryService();

        BatteryChecker bc = new BatteryChecker();
        bc.setAlarm(getApplicationContext());

    }


    @Override
    public void onClick(View v) {
        activateBtn.setText("hello");

        int id = v.getId();

        if(id != activateBtn.getId()){
            activateBtn.setText("nothing");
        }

    }
}
