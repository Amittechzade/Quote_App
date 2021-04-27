package com.arkapps.dailyquotes;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

import com.arkapps.dailyquotes.cls.MySharePreference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MyApplication extends Application {

  public static   FirebaseFirestore firestore;

    @Override
    public void onCreate() {
        super.onCreate();
        firestore  = FirebaseFirestore.getInstance();
        changeUi();
    }

    private void changeUi(){
        boolean isDarkModeEnabled = MySharePreference.getInstance(getApplicationContext()).isDarkMode();

        if (isDarkModeEnabled){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


}
