package au.com.patricklabes.sleepeasy;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by patri on 19-Aug-17.
 */

 class SharedPrefrenceInformationManager {

    private SharedPreferences prefs;
    private Context context;

     SharedPrefrenceInformationManager(Context context){
        this.context = context;
         prefs = context.getSharedPreferences("au.com.shifttech", 0);

    }


     int getStartTime(){
        SharedPreferences prefs = context.getSharedPreferences("au.com.shifttech", 0);

        int startTime = prefs.getInt("STARTTIME",22);

        return startTime;
    }

     int getEndTime(){
        SharedPreferences prefs = context.getSharedPreferences("au.com.shifttech", 0);

        int endTime = prefs.getInt("ENDTIME",2);

        return endTime;
    }

    boolean activationButton(){
        return prefs.getBoolean("ACTIVATED",false);
    }

    void changeActivationButtonStatus(boolean position){
        if (position){
            prefs.edit().putBoolean("ACTIVATED",true).apply();
        }else {
            prefs.edit().putBoolean("ACTIVATED",false).apply();
        }

    }

    void setSwitch(String switchThatNeedsToFlip){

        boolean position = prefs.getBoolean(switchThatNeedsToFlip,false);

        if (position){
            prefs.edit().putBoolean(switchThatNeedsToFlip,false).apply();
        }else{
            prefs.edit().putBoolean(switchThatNeedsToFlip,true).apply();
        }

    }

    boolean getRingerSwitch(){
        return prefs.getBoolean("RINGER",false);
    }

    boolean getFlashSwitch(){
        return prefs.getBoolean("FLASH",false);
    }


}
