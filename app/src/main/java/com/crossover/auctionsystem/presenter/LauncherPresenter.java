package com.crossover.auctionsystem.presenter;

import com.crossover.auctionsystem.interactor.LauncherInteractor;
import com.crossover.auctionsystem.view.LauncherView;

/**
 * Created by suraj on 23/9/16.
 */

public class LauncherPresenter {
    private LauncherView mView;
    private LauncherInteractor mInteractor;

    public LauncherPresenter(LauncherView view, LauncherInteractor launcherInteractor) {
        this.mView = view;
        this.mInteractor = launcherInteractor;
    }

    public void startInitialActivity() {
        if(mInteractor.isUserLoggedIn()) {
            mView.startItemsAvailableForBidActivity();
        } else {
            mView.startSignupActivity();
        }
    }
}
