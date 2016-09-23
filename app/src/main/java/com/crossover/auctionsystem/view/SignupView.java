package com.crossover.auctionsystem.view;

/**
 * Created by suraj on 23/9/16.
 */

public interface SignupView {
    String getUsername();

    String getPassword();

    void showUsernameLengthInvalidError();

    void showPasswordLengthInvalidError();

    void showUserExistsError();

    void startItemsAvailableForBidActivity();

    void startLoginActivity();

    void showNameLengthInvalidError();

    String getName();
}
