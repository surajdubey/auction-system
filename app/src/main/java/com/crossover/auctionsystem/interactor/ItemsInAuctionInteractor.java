package com.crossover.auctionsystem.interactor;

import android.content.Context;

import com.crossover.auctionsystem.db.ItemDataSource;
import com.crossover.auctionsystem.utils.PreferencesManager;

/**
 * Created by suraj on 23/9/16.
 */

public class ItemsInAuctionInteractor {
    private PreferencesManager mPreferencesManager;
    private ItemDataSource mItemDataSource;

    public ItemsInAuctionInteractor(Context context) {
        mPreferencesManager = PreferencesManager.initializeInstance(context);
        mItemDataSource = new ItemDataSource(context);
    }

    public void fetchAllItems() {

    }
}
