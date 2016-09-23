package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.SignupInteractor;
import com.crossover.auctionsystem.view.SignupView;

/**
 * Created by suraj on 23/9/16.
 */

public class SignupPresenter {
    private SignupView mView;
    private SignupInteractor mInteractor;

    public SignupPresenter(SignupView signupView, SignupInteractor signupInteractor) {
        this.mView = signupView;
        this.mInteractor = signupInteractor;
    }

    public void signup() {
        String name = mView.getName();
        String username = mView.getUsername();
        String password = mView.getPassword();

        if (name.length() < 5) {
            mView.showNameLengthInvalidError();
            return;
        }

        if (username.length() < 5) {
            mView.showUsernameLengthInvalidError();
            return;
        }

        if (password.length() < 5) {
            mView.showPasswordLengthInvalidError();
            return;
        }

        if (mInteractor.userExists(username)) {
            mView.showUserExistsError();
            return;
        }

        mInteractor.addUser(username, password);

        mView.startItemsAvailableForBidActivity();
    }

    public void login() {
        mView.startLoginActivity();
    }
}
