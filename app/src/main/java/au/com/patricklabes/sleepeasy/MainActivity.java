package au.com.patricklabes.sleepeasy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView, info2;
    private int batteryPercent, pluggedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = this.getSharedPreferences("au.com.shifttech", 0);




        textView = (TextView)this.findViewById(R.id.infoText);
        info2 = (TextView)this.findViewById(R.id.info2);


       // startBatteryService();

        BatteryChecker bc = new BatteryChecker();
        bc.setAlarm(getApplicationContext());

    }



}
