package au.com.patricklabes.sleepeasy;

import android.content.Context;


public class DaySelection {

    SharedPreferenceInformationManager mI;

    public DaySelection(Context context) {
        mI = new SharedPreferenceInformationManager(context);

    }


    public void radioButtonSelected(String day){
        if(mI.getRadioButtonPosition(day)){
            mI.setRadioButton(day, false);
        }else{
            mI.setRadioButton(day,true);
        }

    }







}
