package au.com.patricklabes.sleepeasy;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


class SharedPreferenceInformationManager {

    private SharedPreferences prefs;
    private Context context;

     SharedPreferenceInformationManager(Context context){
        this.context = context;
         prefs = context.getSharedPreferences("au.com.shifttech", 0);

    }


     int getStartTime(){
        SharedPreferences prefs = context.getSharedPreferences("au.com.shifttech", 0);

        return prefs.getInt("STARTTIME",22);
    }

     int getEndTime(){
        SharedPreferences prefs = context.getSharedPreferences("au.com.shifttech", 0);

        return prefs.getInt("ENDTIME",2);
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

        Log.d("Switch that ","flipped");
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

    void setNotificationStatus(boolean position){
        position = prefs.getBoolean("NOTIFICATION",false);
        prefs.edit().putBoolean("NOTIFICATION",position).apply();
    }

    boolean getNotificationStatus(){
        return prefs.getBoolean("NOTIFICATION",false);
    }

    void setPause(){
        prefs.edit().putBoolean("PAUSE_ALERT",true).apply();
    }

    void resetPause(){
        prefs.edit().putBoolean("PAUSE_ALERT",false).apply();
    }

    boolean positionPause(){
        return prefs.getBoolean("PAUSE_ALERT",false);
    }



}
