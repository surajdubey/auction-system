package com.crossover.auctionsystem.interactor;

import android.content.Context;

import com.crossover.auctionsystem.db.UserDataSource;
import com.crossover.auctionsystem.model.User;
import com.crossover.auctionsystem.utils.PreferencesManager;

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

    public boolean login(String username, String password) {
        mUserDataSource.open();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        long userId = mUserDataSource.userLogin(user);
        mUserDataSource.close();

        if (userId == UserDataSource.INVALID_USER_ID) {
            return false;
        } else {
            /**
             * save this data in Preferences
             */
            mPreferencesManager.setUserLoggedIn(true);
            mPreferencesManager.setUserId(userId);

            return true;
        }
    }
}
