package com.crossover.auctionsystem.interactor;

import android.content.Context;

import com.crossover.auctionsystem.utils.PreferencesManager;

/**
 * Created by suraj on 22/9/16.
 */

public class LauncherInteractor {
    private PreferencesManager mPreferencesManager;
    public LauncherInteractor(Context context) {
        mPreferencesManager = PreferencesManager.initializeInstance(context);
    }

    public boolean isUserLoggedIn() {
        return mPreferencesManager.isUserLoggedIn();
    }
}
