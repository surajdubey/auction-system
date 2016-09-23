package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.LoginInteractor;
import com.crossover.auctionsystem.view.LoginView;

/**
 * Created by suraj on 23/9/16.
 */

public class LoginPresenter {
    private LoginView mView;
    private LoginInteractor mInteractor;
    public LoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
        this.mView = loginView;
        this.mInteractor = loginInteractor;
    }

    public void login() {
        String username = mView.getUsername();
        String password = mView.getPassword();

        if(username.length()<5) {
            mView.showUsernameLengthInvalidError();
            return;
        }

        if(password.length()<5) {
            mView.showPasswordLengthInvalidError();
            return;
        }

        if(mInteractor.login(username, password)) {
            mView.startItemsAvailableForBidActivity();
        } else {
            mView.showInvalidCredentialsError();
        }
    }

    public void signup() {
        mView.signup();
    }

}
