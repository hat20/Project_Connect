package com.example.ht2s.projectconnect;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by HT2S on 18-04-2018.
 */

public class PrefManager {
    Context context;
    public PrefManager(Context context){
        this.context=context;
    }
    public void saveSession(boolean sessionId){
        SharedPreferences sp=context.getSharedPreferences("session",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("session",sessionId);
        editor.commit();
    }
    public boolean getSession(){
        SharedPreferences sp=context.getSharedPreferences("session",Context.MODE_PRIVATE);
        if(sp.getBoolean("session",true)){
            return true;
        }
        return false;
    }
}
