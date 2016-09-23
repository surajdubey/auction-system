package com.crossover.auctionsystem.view;

/**
 * Created by suraj on 23/9/16.
 */

public interface LoginView {
    void signup();

    void startItemsAvailableForBidActivity();

    void showPasswordInvalidError();

    void showUsernameInvalidError();

    String getPassword();

    String getUsername();

    void showInvalidCredentialsError();
}
