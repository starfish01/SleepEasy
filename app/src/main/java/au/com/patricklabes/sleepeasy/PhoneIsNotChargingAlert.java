package au.com.patricklabes.sleepeasy;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by patri on 03-Sep-17.
 */

public class PhoneIsNotChargingAlert extends AppCompatActivity{

    SharedPreferenceInformationManager mI;
    Boolean flasher, ringer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);

        mI = new SharedPreferenceInformationManager(this);
        ringer = mI.getRingerSwitch();
        flasher = mI.getFlashSwitch();

        //launch

    }
}
