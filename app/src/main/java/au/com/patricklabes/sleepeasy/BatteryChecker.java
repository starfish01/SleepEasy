package au.com.patricklabes.sleepeasy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by patri on 01-Aug-17.
 */

public class BatteryChecker extends Activity implements View.OnClickListener {
    private View preferenceBtn;

    @Override
    public void onCreate(Bundle icicle){
        super.onCreate(icicle);

        setContentView(R.layout.activity_main);
        this.preferenceBtn = (Button)this.findViewById(R.id.btn);
        this.preferenceBtn.setOnClickListener(this);


    }




    @Override
    public void onClick(View v) {


    }
}
