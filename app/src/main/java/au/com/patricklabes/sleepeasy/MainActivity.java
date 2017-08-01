package au.com.patricklabes.sleepeasy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView, info2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)this.findViewById(R.id.infoText);
        info2 = (TextView)this.findViewById(R.id.info2);


        this.registerReceiver(this.mBatInfoReceiver,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


        this.registerReceiver(this.chargeReceiver,
                new IntentFilter(Intent.ACTION_POWER_CONNECTED));

        this.registerReceiver(this.chargeReceiveroff,
                new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));


    }




    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level",0);
            textView.setText(String.valueOf(level)+"%");


        }
    };

    private BroadcastReceiver chargeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           info2.setText("power connected");
        }
    };

    private BroadcastReceiver chargeReceiveroff = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            info2.setText("power disconnected");
        }
    };











}
