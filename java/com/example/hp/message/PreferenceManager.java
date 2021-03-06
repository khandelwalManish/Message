package com.example.hp.message;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hp.message.R;

public class PreferenceManager {

    private SharedPreferences sharedPreferences;
    private Context context;

    public PreferenceManager(Context context)
    {
        this.context = context;
        getSharedPreference();
    }

    private void getSharedPreference()
    {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.my_preference),Context.MODE_PRIVATE);
    }

    public void writePreference()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.my_pref_key),"INIT_OK");
        editor.commit();
    }

    public boolean checkPreference() {
        boolean status = false;

        if (sharedPreferences.getString(context.getString(R.string.my_pref_key),"null").equals("null"))
        {
            status=false;
        }
        else
        {
            status=true;
        }
        return status;
    }

    public void clearPreference()
    {
        sharedPreferences.edit().clear().commit();
    }

}


