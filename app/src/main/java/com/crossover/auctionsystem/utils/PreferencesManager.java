package com.crossover.auctionsystem.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.crossover.auctionsystem.db.UserDataSource;

public class PreferencesManager {
    private static final String SHARED_PREFERENCE_KEY = "auction_system";
    private static final String LOGGED_IN_KEY = "logged_in";
    private static final String KEY_USER_ID = "user_id";

    //singleton instance
    private static PreferencesManager sPreferencesManagerInstance;
    private SharedPreferences mPreferences;

    private PreferencesManager(Context context) {
        mPreferences = context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
    }

    public static synchronized PreferencesManager initializeInstance(Context context) {
        if (sPreferencesManagerInstance == null) {
            sPreferencesManagerInstance = new PreferencesManager(context);
        }
        return sPreferencesManagerInstance;
    }


    public boolean isUserLoggedIn() {
        return mPreferences.getBoolean(LOGGED_IN_KEY, false);
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(LOGGED_IN_KEY, loggedIn);
        editor.apply();
    }

    public int getUserId() {
        return (int) mPreferences.getLong(KEY_USER_ID, UserDataSource.INVALID_USER_ID);
    }

    public void setUserId(long userId) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putLong(KEY_USER_ID, userId);
        editor.apply();
    }

    public void logout() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
