package com.crossover.auctionsystem.interactor;

import android.content.Context;

import com.crossover.auctionsystem.utils.PreferencesManager;

/**
 * Created by suraj on 23/9/16.
 */

public class ItemsInAuctionInteractor {
    private PreferencesManager mPreferencesManager;
    public ItemsInAuctionInteractor(Context context) {
        mPreferencesManager = PreferencesManager.initializeInstance(context);
    }
}
