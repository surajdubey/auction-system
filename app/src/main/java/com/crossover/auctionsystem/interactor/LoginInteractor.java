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

        boolean userExists = mUserDataSource.userLogin(user) == UserDataSource.INVALID_USER_ID;;
        mUserDataSource.close();
        return userExists;
    }
}
