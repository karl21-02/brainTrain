package com.example.braintrain;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

//    static String name = "user_prefs";
    static String motivation = "motivation";

    public static void saveMotivation(Context context, String motivation) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(motivation, motivation);
//        editor.apply();
        PreferenceManager.motivation = motivation;
    }

    public static String getMotivation(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return PreferenceManager.motivation;
    }
}
