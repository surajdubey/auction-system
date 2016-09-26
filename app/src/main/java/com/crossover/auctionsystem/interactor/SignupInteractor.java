package com.crossover.auctionsystem.interactor;

import android.content.Context;

import com.crossover.auctionsystem.db.UserDataSource;
import com.crossover.auctionsystem.model.User;
import com.crossover.auctionsystem.utils.DateTimeUtil;
import com.crossover.auctionsystem.utils.PreferencesManager;

/**
 * Created by suraj on 23/9/16.
 */

public class SignupInteractor {
    private PreferencesManager preferencesManager;
    private UserDataSource mUserDataSource;

    public SignupInteractor(Context context) {
        preferencesManager = PreferencesManager.initializeInstance(context);
        mUserDataSource = new UserDataSource(context);
    }

    public boolean userExists(String username) {
        mUserDataSource.open();

        User user = new User();
        user.setUsername(username);

        boolean userExists = mUserDataSource.getUserIdIfUserExists(user) != UserDataSource.INVALID_USER_ID;

        mUserDataSource.close();
        return userExists;
    }

    public void addUser(String username, String displayName, String password) {

        String currentTime = new DateTimeUtil().getCurrentDateTime();

        mUserDataSource.open();

        User user = new User();
        user.setUsername(username);
        user.setName(displayName);
        user.setPassword(password);
        user.setCreatedAt(currentTime);

        long userId = mUserDataSource.addUser(user);

        /**
         * save this data in Preferences
         */

        preferencesManager.setUserLoggedIn(true);
        preferencesManager.setUserId(userId);
    }
}
