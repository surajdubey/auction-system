package com.crossover.auctionsystem.interactor;

import android.content.Context;

import com.conferencemanager.android.db.UserDataSource;
import com.conferencemanager.android.utils.PreferencesManager;

/**
 * Created by suraj on 22/9/16.
 */

public class LoginInteractor {

    private UserDataSource mUserDataSource;
    private PreferencesManager mPreferencesManager;

    public LoginInteractor(Context context) {
        mUserDataSource = new UserDataSource(context);
        mPreferencesManager = PreferencesManager.initializeInstance(context);
    }


}
